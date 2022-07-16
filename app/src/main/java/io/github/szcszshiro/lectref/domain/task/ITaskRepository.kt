package io.github.szcszshiro.lectref.domain.task

import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface ITaskRepository {
    fun getFlow(): Flow<List<Task>>
    suspend fun findAll(): List<Task>
    suspend fun findFromId(id: Int): Task?
    suspend fun findFromLectureId(id: Int): List<Task>
    suspend fun add(task: Task)
    suspend fun remove(task: Task)
    suspend fun update(
        id: Int,
        newName: String?,
        newDescription: String?,
        newDeadLine: LocalDateTime?,
        isDone: Boolean?
    )
}