package io.github.szcszshiro.lectref.domain.lecture

import kotlinx.coroutines.flow.Flow

interface ILectureRepository {
    fun getFlow(): Flow<List<Lecture>>
    suspend fun findAll(): List<Lecture>
    suspend fun findFromId(id: Int): Lecture?
    suspend fun add(lecture: Lecture)
    suspend fun remove(lecture: Lecture)
    suspend fun update(lecture: Lecture)
}