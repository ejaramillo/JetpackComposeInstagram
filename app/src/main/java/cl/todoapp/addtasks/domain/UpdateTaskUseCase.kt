package cl.todoapp.addtasks.domain

import cl.todoapp.addtasks.data.TaskRepository
import cl.todoapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(taskModel: TaskModel){
        taskRepository.update(taskModel = taskModel)
    }
}
