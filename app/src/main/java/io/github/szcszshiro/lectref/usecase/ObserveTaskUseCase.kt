package io.github.szcszshiro.lectref.usecase

import io.github.szcszshiro.lectref.infrastructure.TaskRepository
import javax.inject.Inject

class ObserveTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
){
    fun getFlow() = taskRepository.getFlow()
}