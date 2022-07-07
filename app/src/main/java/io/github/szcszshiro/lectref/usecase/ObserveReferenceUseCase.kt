package io.github.szcszshiro.lectref.usecase

import io.github.szcszshiro.lectref.infrastructure.ReferenceRepository
import javax.inject.Inject

class ObserveReferenceUseCase @Inject constructor(
    private val referenceRepository: ReferenceRepository
){
    fun getFlow() = referenceRepository.getFlow()
}