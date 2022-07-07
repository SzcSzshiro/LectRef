package io.github.szcszshiro.lectref.usecase

import io.github.szcszshiro.lectref.domain.task.Task
import io.github.szcszshiro.lectref.infrastructure.TaskRepository
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import javax.inject.Inject

class RecordTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
){
    fun addTask(
        lectureId: Int,
        name: String,
        description: String,
        deadLine: LocalDateTime
    ){
        val newTask = Task.create(lectureId, name, description, deadLine, false)?: return
        runBlocking{
            taskRepository.add(newTask)
        }
    }

    fun removeTask(id: Int){
        runBlocking {
            val target = taskRepository.findFromId(id)?: return@runBlocking
            taskRepository.remove(target)
        }
    }
}