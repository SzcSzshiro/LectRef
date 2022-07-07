package io.github.szcszshiro.lectref.usecase

import io.github.szcszshiro.lectref.domain.reference.Reference
import io.github.szcszshiro.lectref.infrastructure.LectureRepository
import io.github.szcszshiro.lectref.infrastructure.ReferenceRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class RecordReferenceUseCase @Inject constructor(
    private val referenceRepository: ReferenceRepository
){
    fun addReference(
        lectureId: Int,
        name: String,
        urls: List<String>,
        description: String
    ){
        val newReference = Reference.create(lectureId, name, urls, description)?: return
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
}