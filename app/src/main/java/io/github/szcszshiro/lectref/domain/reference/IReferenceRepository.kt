package io.github.szcszshiro.lectref.domain.reference

import kotlinx.coroutines.flow.Flow

interface IReferenceRepository {
    fun getFlow(): Flow<List<Reference>>
    suspend fun findAll(): List<Reference>
    suspend fun findFromLectureId(id: Int): Reference?
    suspend fun add(reference: Reference)
    suspend fun remove(reference: Reference)
}