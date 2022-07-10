package io.github.szcszshiro.lectref.usecase

import io.github.szcszshiro.lectref.infrastructure.TaskRepository
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject

class ObserveTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
){
    fun getFlow() =
        taskRepository
            .getFlow()
            .map { list ->
                list.map { task ->
                    TaskDTO(
                        task.id!!,
                        task.lectureId,
                        task.name,
                        task.description,
                        task.deadLine,
                        task.isDone
                    )
                }
            }

    data class TaskDTO(
        val id: Int,
        val lectureId: Int,
        val name: String,
        val description: String,
        val deadLine: LocalDateTime,
        val isDone: Boolean
    )
}