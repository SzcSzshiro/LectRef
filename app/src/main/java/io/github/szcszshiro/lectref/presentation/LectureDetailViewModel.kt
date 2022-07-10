package io.github.szcszshiro.lectref.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.szcszshiro.lectref.usecase.ObserveLectureUseCase
import io.github.szcszshiro.lectref.usecase.ObserveReferenceUseCase
import io.github.szcszshiro.lectref.usecase.ObserveTaskUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import kotlin.jvm.Throws

@HiltViewModel
class LectureDetailViewModel @Inject constructor(
    private val observeLectureUseCase: ObserveLectureUseCase,
    private val observeTaskUseCase: ObserveTaskUseCase,
    private val observeReferenceUseCase: ObserveReferenceUseCase
): ViewModel() {
    @Throws()
    fun getLectureDetailLiveData(lectureId: Int): Flow<LectureDetailData> =
        combine(
            observeLectureUseCase.getFlow(),
            observeTaskUseCase.getFlow(),
            observeReferenceUseCase.getFlow()
        ){ lectures, tasks, references ->
            val targetLecture = lectures.first { it.id == lectureId }
            val targetTasks = kotlin.runCatching {
                tasks.filter { it.lectureId == lectureId }
            }.getOrDefault(emptyList())
            val targetReferences = kotlin.runCatching {
                references.filter { it.lectureId == lectureId }
            }.getOrDefault(emptyList())

            LectureDetailData(
                targetLecture,
                targetTasks,
                targetReferences
            )
        }

    data class LectureDetailData(
        val lectureDTO: ObserveLectureUseCase.LectureDTO,
        val taskDTOs: List<ObserveTaskUseCase.TaskDTO>,
        val referenceDTOs: List<ObserveReferenceUseCase.ReferenceDTO>
    )
}