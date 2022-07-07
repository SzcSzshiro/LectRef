package io.github.szcszshiro.lectref.usecase

import io.github.szcszshiro.lectref.infrastructure.LectureRepository
import javax.inject.Inject

class ObserveLectureUseCase @Inject constructor(
    private val lectureRepository: LectureRepository
){
    fun getFlow() = lectureRepository.getFlow()
}