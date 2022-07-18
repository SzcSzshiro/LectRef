package io.github.szcszshiro.lectref.usecase

import io.github.szcszshiro.lectref.domain.reference.Reference
import io.github.szcszshiro.lectref.domain.task.Task
import io.github.szcszshiro.lectref.infrastructure.LectureRepository
import io.github.szcszshiro.lectref.infrastructure.ReferenceRepository
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import javax.inject.Inject

class RecordReferenceUseCase @Inject constructor(
    private val referenceRepository: ReferenceRepository
){
    fun addReference(
        lectureId: Int,
        name: String,
        url: String,
        description: String
    ){
        val newReference = Reference.create(lectureId, name, url, description)?: return
        runBlocking {
            referenceRepository.add(newReference)
        }
    }

    fun removeReference(id: Int){
        runBlocking {
            val target = referenceRepository.findFromId(id)?: return@runBlocking
            referenceRepository.remove(target)
        }
    }

    fun editTask(
        id: Int,
        name: String?,
        description: String?,
        url: String?
    ){
        runBlocking {
            referenceRepository.update(id, name, description, url)
        }
    }

    fun checkIsNameOk(newName: String) = Reference.isNameOk(newName)

    fun checkIsDescriptionOk(newDescription: String) = Reference.isDescriptionOk(newDescription)
}