package io.github.szcszshiro.lectref.usecase

import io.github.szcszshiro.lectref.infrastructure.ReferenceRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObserveReferenceUseCase @Inject constructor(
    private val referenceRepository: ReferenceRepository
){
    fun getFlow() =
        referenceRepository
            .getFlow()
            .map { list ->
                list.map { reference ->
                    ReferenceDTO(
                        reference.id!!,
                        reference.lectureId,
                        reference.name,
                        reference.url,
                        reference.description
                    )
                }
            }

    data class ReferenceDTO(
        val id: Int,
        val lectureId: Int,
        val name: String,
        val url: String,
        val description: String
    )
}