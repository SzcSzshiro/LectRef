package io.github.szcszshiro.lectref.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.szcszshiro.lectref.usecase.ObserveTaskUseCase
import io.github.szcszshiro.lectref.usecase.RecordTaskUseCase
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val observeTaskUseCase: ObserveTaskUseCase,
    private val recordTaskUseCase: RecordTaskUseCase
): ViewModel() {
    suspend fun getTaskDTO(id: Int) =
        observeTaskUseCase.findFromId(id)

    fun addTask(
        lectureId: Int,
        name: String,
        description: String,
        deadLine: LocalDateTime
    ){
        recordTaskUseCase.addTask(lectureId, name, description, deadLine)
    }

    fun editTask(
        id: Int,
        name: String?,
        description: String?,
        deadLine: LocalDateTime?,
        isDone: Boolean?
    ){
        recordTaskUseCase.editTask(id, name, description, deadLine, isDone)
    }

    fun checkIsNameOk(newName: String) = recordTaskUseCase.checkIsNameOk(newName)

    fun checkIsDescriptionOk(newDescription: String) = recordTaskUseCase.checkIsDescriptionOk(newDescription)

}