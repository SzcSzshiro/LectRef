package io.github.szcszshiro.lectref.domain.lecture

import kotlinx.coroutines.flow.Flow
import java.time.DayOfWeek
import java.time.LocalTime

interface ILectureRepository {
    fun getFlow(): Flow<List<Lecture>>
    suspend fun findAll(): List<Lecture>
    suspend fun findFromId(id: Int): Lecture?
    suspend fun add(lecture: Lecture)
    suspend fun remove(lecture: Lecture)
    suspend fun update(
        id: Int,
        newName: String?,
        newDescription: String?,
        newWeek: DayOfWeek?,
        newStartTime: LocalTime?
    )
}