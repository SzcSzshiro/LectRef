package io.github.szcszshiro.lectref.domain.task

import kotlinx.coroutines.flow.Flow

interface ITaskRepository {
    fun getFlow(): Flow<List<Task>>
    suspend fun findAll(): List<Task>
    suspend fun findFromId(id: Int): Task?
    suspend fun findFromLectureId(id: Int): List<Task>
    suspend fun add(task: Task)
    suspend fun remove(task: Task)
}