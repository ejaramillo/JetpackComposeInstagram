package cl.todoapp.addtasks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.todoapp.addtasks.domain.AddTaskUserCase
import cl.todoapp.addtasks.domain.DeleteTaskUseCase
import cl.todoapp.addtasks.domain.GetTasksUseCase
import cl.todoapp.addtasks.domain.UpdateTaskUseCase
import cl.todoapp.addtasks.ui.model.TaskModel
import cl.todoapp.addtasks.ui.model.TasksUiState
import cl.todoapp.addtasks.ui.model.TasksUiState.Error
import cl.todoapp.addtasks.ui.model.TasksUiState.Loading
import cl.todoapp.addtasks.ui.model.TasksUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUserCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    getTasksUseCase: GetTasksUseCase
) : ViewModel() {


    val uiState: StateFlow<TasksUiState> = getTasksUseCase().map(::Success)
        .catch{ Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000), Loading)


    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    /*private val _tasks = mutableStateListOf<TaskModel>()
    val task: List<TaskModel> = _tasks*/

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTasksCreated(task: String) {
        _showDialog.value = false
        /*_tasks.add(TaskModel(task = task))*/

        viewModelScope.launch {
            addTaskUseCase(taskModel = TaskModel(task = task))
        }
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        // Actualizar check
        /*val index = _tasks.indexOf(taskModel)
        _tasks[index] = _tasks[index].let {
            it.copy(selected = !it.selected)
        }*/
        viewModelScope.launch {
            updateTaskUseCase(taskModel = taskModel.copy(selected = !taskModel.selected))
        }
    }

    fun onItemRemove(taskModel: TaskModel) {
        /*// de esta forma solo se borran algunos elementos, no todos
        //_tasks.remove(element = taskModel)
        val task = _tasks.find { it.id == taskModel.id }
        _tasks.remove(element = task)*/
        viewModelScope.launch {
            deleteTaskUseCase.invoke(taskModel = taskModel)
        }
    }


}
