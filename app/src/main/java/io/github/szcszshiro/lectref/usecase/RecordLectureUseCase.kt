package io.github.szcszshiro.lectref.usecase

import io.github.szcszshiro.lectref.domain.lecture.Lecture
import io.github.szcszshiro.lectref.infrastructure.LectureRepository
import io.github.szcszshiro.lectref.infrastructure.ReferenceRepository
import io.github.szcszshiro.lectref.infrastructure.TaskRepository
import kotlinx.coroutines.runBlocking
import java.lang.IllegalArgumentException
import java.time.DayOfWeek
import java.time.LocalTime
import javax.inject.Inject

class RecordLectureUseCase @Inject constructor(
    private val lectureRepository: LectureRepository,
    private val referenceRepository: ReferenceRepository,
    private val taskRepository: TaskRepository
){
    @Throws(IllegalArgumentException::class)
    fun addLecture(
        name: String,
        description: String,
        week: DayOfWeek,
        startTime: LocalTime
    ){
        require(Lecture.isNameOk(name) && Lecture.isDescriptionOk(description))
        val newLecture = Lecture.create(name, description, week, startTime)?: return
        runBlocking {
            lectureRepository.add(newLecture)
        }
    }

    fun removeLecture(id: Int){
        runBlocking {
            val target = lectureRepository.findFromId(id) ?: return@runBlocking
            val targetReferences = referenceRepository.findFromLectureId(id)
            val targetTasks = taskRepository.findFromLectureId(id)

            targetReferences.forEach {
                referenceRepository.remove(it)
            }
            targetTasks.forEach {
                taskRepository.remove(it)
            }
            lectureRepository.remove(target)
        }
    }
}