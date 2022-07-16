package io.github.szcszshiro.lectref.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.szcszshiro.lectref.usecase.ObserveLectureUseCase
import io.github.szcszshiro.lectref.usecase.ObserveReferenceUseCase
import io.github.szcszshiro.lectref.usecase.ObserveTaskUseCase
import io.github.szcszshiro.lectref.usecase.RecordLectureUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LectureListViewModel @Inject constructor(
    private val observeLectureUseCase: ObserveLectureUseCase,
    private val observeTaskUseCase: ObserveTaskUseCase,
): ViewModel() {
    val lectureCardsLiveData: LiveData<List<LectureCardData>> = run{
        val lectureFlow = observeLectureUseCase.getFlow()
        val taskFlow = observeTaskUseCase.getFlow()
        combine(lectureFlow, taskFlow, ::Pair).map { pair ->
            pair.first.map { lecture ->
                LectureCardData(
                    lecture.id,
                    lecture.name,
                    pair.second.filter { it.lectureId == lecture.id && !it.isDone}.size,
                    lecture.week.name,
                    "${lecture.startTime.hour}:${lecture.startTime.minute}~"
                )
            }
        }.asLiveData()
    }

    data class LectureCardData(
        val id: Int,
        val lectureName: String,
        val taskNum: Int,
        val weekValue: String,
        val startTimeValue: String
    )
}