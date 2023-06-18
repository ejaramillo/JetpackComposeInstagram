package cl.todoapp.addtasks.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import cl.todoapp.addtasks.ui.model.TaskModel
import cl.todoapp.addtasks.ui.model.TasksUiState
import cl.todoapp.addtasks.ui.model.TasksUiState.Error
import cl.todoapp.addtasks.ui.model.TasksUiState.Loading
import cl.todoapp.addtasks.ui.model.TasksUiState.Success

@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {
    val showDialog: Boolean by tasksViewModel.showDialog.observeAsState(initial = false)
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val uiState by produceState<TasksUiState>(
        initialValue = TasksUiState.Loading,
        key1 = lifecycle,
        key2 = tasksViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            tasksViewModel.uiState.collect {
                value = it
            }
        }
    }

    Log.i("TasksScreen", "--> ${uiState.javaClass}")

    when (uiState) {
        is Error -> {}
        Loading -> {
            CircularProgressIndicator()
        }
        is Success -> {
            Box(modifier = Modifier.fillMaxSize()) {
                AddTasksDialog(
                    show = showDialog,
                    onDismiss = { tasksViewModel.onDialogClose() },
                    onTaskAdded = { tasksViewModel.onTasksCreated(it) }
                )
                FabDialog(
                    Modifier.align(alignment = Alignment.BottomEnd),
                    tasksViewModel = tasksViewModel
                )
                TasksList((uiState as TasksUiState.Success).tasks, tasksViewModel = tasksViewModel)
            }
        }
    }


}

@Composable
fun TasksList(tasks: List<TaskModel>, tasksViewModel: TasksViewModel) {
    //val myTasks: List<TaskModel> = tasksViewModel.task

    LazyColumn {
        items(tasks, { it.id }) { task ->
           ItemTask(taskModel = task, tasksViewModel = tasksViewModel)
        }
    }
}
/*
@Composable
fun TasksList(tasksViewModel: TasksViewModel) {
    //val myTasks: List<TaskModel> = tasksViewModel.task

    LazyColumn {
        items(myTasks, { it.id }) { task ->
            ItemTask(taskModel = task, tasksViewModel = tasksViewModel)
        }
    }
}*/

@Composable
fun ItemTask(taskModel: TaskModel, tasksViewModel: TasksViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = {
                    tasksViewModel.onItemRemove(taskModel = taskModel)
                })
            },
        //border = BorderStroke(width = 2.dp, color = Color.Magenta),
        elevation = 8.dp
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = taskModel.task,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .weight(weight = 1f)
            )
            Checkbox(
                checked = taskModel.selected,
                onCheckedChange = { tasksViewModel.onCheckBoxSelected(taskModel) })
        }
    }
}

@Composable
fun FabDialog(modifier: Modifier, tasksViewModel: TasksViewModel) {
    FloatingActionButton(
        onClick = {
            tasksViewModel.onShowDialogClick()
        },
        modifier = modifier
            .padding(all = 16.dp)
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }
}

@Composable
fun AddTasksDialog(show: Boolean, onDismiss: () -> Unit, onTaskAdded: (String) -> Unit) {
    var myTask by remember { mutableStateOf("") }
    if (show) {
        Dialog(onDismissRequest = { onDismiss.invoke() }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(all = 16.dp)
            ) {
                Text(
                    text = "Añade tu tarea",
                    fontSize = 18.sp,
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(size = 16.dp))
                TextField(
                    value = myTask,
                    onValueChange = { myTask = it },
                    singleLine = true,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.size(size = 16.dp))
                Button(onClick = {
                    onTaskAdded.invoke(myTask)
                    myTask = ""
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Añadir tarea")
                }
            }
        }
    }
}
