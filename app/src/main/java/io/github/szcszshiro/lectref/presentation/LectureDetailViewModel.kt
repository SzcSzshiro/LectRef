package io.github.szcszshiro.lectref.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.szcszshiro.lectref.usecase.ObserveLectureUseCase
import io.github.szcszshiro.lectref.usecase.ObserveReferenceUseCase
import io.github.szcszshiro.lectref.usecase.ObserveTaskUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.jvm.Throws

@HiltViewModel
class LectureDetailViewModel @Inject constructor(
    private val observeLectureUseCase: ObserveLectureUseCase,
    private val observeTaskUseCase: ObserveTaskUseCase,
    private val observeReferenceUseCase: ObserveReferenceUseCase
): ViewModel() {
    fun getLectureDetailLiveData(id: Int) =
        observeLectureUseCase
            .getFlow()
            .map { list ->
                list.first { it.id == id }
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
}