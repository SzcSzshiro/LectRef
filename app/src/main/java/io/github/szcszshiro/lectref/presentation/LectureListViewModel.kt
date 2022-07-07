package io.github.szcszshiro.lectref.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.szcszshiro.lectref.usecase.ObserveLectureUseCase
import io.github.szcszshiro.lectref.usecase.ObserveTaskUseCase
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class LectureListViewModel @Inject constructor(
    private val observeLectureUseCase: ObserveLectureUseCase,
    private val observeTaskUseCase: ObserveTaskUseCase
): ViewModel() {
    fun getLectureLivedata() = observeLectureUseCase.getFlow().asLiveData()

    fun getTasksLivedata(lectureId: Int? = null) =
        observeTaskUseCase.getFlow().map { list ->
            if (lectureId != null) {
                return@map list.filter {
                    it.lectureId == lectureId
                }
            } else {
                return@map list
            }
        }.asLiveData()
}