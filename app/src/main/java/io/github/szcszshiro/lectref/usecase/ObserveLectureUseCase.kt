package io.github.szcszshiro.lectref.usecase

import io.github.szcszshiro.lectref.infrastructure.LectureRepository
import kotlinx.coroutines.flow.map
import java.time.DayOfWeek
import java.time.LocalTime
import javax.inject.Inject

class ObserveLectureUseCase @Inject constructor(
    private val lectureRepository: LectureRepository
){
    fun getFlow() =
        lectureRepository
            .getFlow()
            .map { list ->
                list.map { lecture ->
                    LectureDTO(
                        lecture.id!!,
                        lecture.name,
                        lecture.description,
                        lecture.week,
                        lecture.startTime
                    )
                }
            }

    data class LectureDTO(
        val id: Int,
        val name: String,
        val description: String,
        val week: DayOfWeek,
        val startTime: LocalTime
    )
}