package io.github.szcszshiro.lectref.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.szcszshiro.lectref.usecase.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.jvm.Throws

@HiltViewModel
class LectureDetailViewModel @Inject constructor(
    private val observeLectureUseCase: ObserveLectureUseCase,
    private val observeTaskUseCase: ObserveTaskUseCase,
    private val observeReferenceUseCase: ObserveReferenceUseCase,
    private val recordLectureUseCase: RecordLectureUseCase,
    private val recordTaskUseCase: RecordTaskUseCase,
    private val recordReferenceUseCase: RecordReferenceUseCase
): ViewModel() {
    fun getLectureDetailLiveData(id: Int) =
        observeLectureUseCase
            .getFlow()
            .map { list ->
                kotlin.runCatching {
                    list.first { it.id == id }
                }.getOrNull()
            }
            .asLiveData()

    fun getTasksLiveData(lectureId: Int) =
        observeTaskUseCase
            .getFlow()
            .map{ list ->
                list.filter {
                    it.lectureId == lectureId
                }
            }
            .asLiveData()

    fun getReferencesLiveData(lectureId: Int) =
        observeReferenceUseCase
            .getFlow()
            .map{ list ->
                list.filter {
                    it.lectureId == lectureId
                }
            }
            .asLiveData()

    suspend fun deleteLecture(lectureId: Int){
        recordLectureUseCase.removeLecture(lectureId)
    }

    suspend fun deleteTask(taskId: Int){
        recordTaskUseCase.removeTask(taskId)
    }

    suspend fun deleteReference(referenceId: Int){
        recordReferenceUseCase.removeReference(referenceId)
    }
}