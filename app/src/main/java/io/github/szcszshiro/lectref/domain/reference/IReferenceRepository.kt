package io.github.szcszshiro.lectref.domain.reference

import kotlinx.coroutines.flow.Flow

interface IReferenceRepository {
    fun getFlow(): Flow<List<Reference>>
    suspend fun findAll(): List<Reference>
    suspend fun findFromId(id: Int): Reference?
    suspend fun findFromLectureId(id: Int): List<Reference>
    suspend fun add(reference: Reference)
    suspend fun remove(reference: Reference)
    suspend fun update(
        id: Int,
        name: String?,
        url: String?,
        description: String?
    )
}