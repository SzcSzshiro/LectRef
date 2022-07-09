package io.github.szcszshiro.lectref.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.szcszshiro.lectref.usecase.ObserveLectureUseCase
import io.github.szcszshiro.lectref.usecase.ObserveReferenceUseCase
import io.github.szcszshiro.lectref.usecase.ObserveTaskUseCase
import io.github.szcszshiro.lectref.usecase.RecordLectureUseCase
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class LectureListViewModel @Inject constructor(
    private val observeLectureUseCase: ObserveLectureUseCase,
    private val observeTaskUseCase: ObserveTaskUseCase,
): ViewModel() {
    fun getLectureLivedata() =
        observeLectureUseCase.getFlow().asLiveData()

    fun getTaskLivedata() =
        observeTaskUseCase.getFlow().asLiveData()
}