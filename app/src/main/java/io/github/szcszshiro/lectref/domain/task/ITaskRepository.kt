package io.github.szcszshiro.lectref.domain.task

import io.github.szcszshiro.lectref.domain.lecture.Lecture
import kotlinx.coroutines.flow.Flow

interface ITaskRepository {
    fun getFlow(): Flow<List<Task>>
    suspend fun findAll(): List<Task>
    suspend fun findFromLectureId(id: Int): Task?
    suspend fun add(task: Task)
    suspend fun remove(task: Task)
}