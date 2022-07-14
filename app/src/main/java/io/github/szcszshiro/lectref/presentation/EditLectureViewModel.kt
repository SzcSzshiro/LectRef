package io.github.szcszshiro.lectref.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.szcszshiro.lectref.usecase.ObserveLectureUseCase
import io.github.szcszshiro.lectref.usecase.RecordLectureUseCase
import java.time.DayOfWeek
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class EditLectureViewModel @Inject constructor(
    private val observeLectureUseCase: ObserveLectureUseCase,
    private val recordLectureUseCase: RecordLectureUseCase
) : ViewModel(){
    suspend fun getLectureDTO(id: Int) =
        observeLectureUseCase.findFromId(id)

    fun addLecture(
        name: String,
        description: String,
        week: DayOfWeek,
        startTime: LocalTime
    ){
        recordLectureUseCase.addLecture(name, description, week, startTime)
    }

    fun editLecture(
        id: Int,
        name: String?,
        description: String?,
        week: DayOfWeek?,
        startTime: LocalTime?
    ){
        recordLectureUseCase.editLecture(id, name, description, week, startTime)
    }
}