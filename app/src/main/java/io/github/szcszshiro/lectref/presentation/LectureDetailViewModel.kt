package io.github.szcszshiro.lectref.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.szcszshiro.lectref.usecase.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
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

    fun deleteLecture(lectureId: Int){
        recordLectureUseCase.removeLecture(lectureId)
    }

    fun deleteTask(taskId: Int){
        recordTaskUseCase.removeTask(taskId)
    }

    fun deleteReference(referenceId: Int){
        recordReferenceUseCase.removeReference(referenceId)
    }

    fun switchTaskIsDone(taskID: Int){
        var target: ObserveTaskUseCase.TaskDTO?
        runBlocking {
            target = observeTaskUseCase.findFromId(taskID)
        }
        if (target == null) return
        recordTaskUseCase.editTask(
            taskID,
            target!!.name,
            target!!.description,
            target!!.deadLine,
            !target!!.isDone
        )
    }
}