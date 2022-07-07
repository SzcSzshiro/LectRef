package io.github.szcszshiro.lectref.infrastructure

import io.github.szcszshiro.lectref.app.db.ITaskDao
import io.github.szcszshiro.lectref.app.db.TaskEntity
import io.github.szcszshiro.lectref.domain.task.ITaskRepository
import io.github.szcszshiro.lectref.domain.task.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val dao: ITaskDao
): ITaskRepository {
    override fun getFlow(): Flow<List<Task>> =
        dao.allAsFlow().map { list ->
            list.map {
                Task.reconstruct(
                    it.id!!,
                    it.lectureId,
                    it.name,
                    it.description,
                    it.deadLine,
                    it.isDone
                )
            }
        }

    override suspend fun findAll(): List<Task> =
        dao.findAll().map {
            Task.reconstruct(
                it.id!!,
                it.lectureId,
                it.name,
                it.description,
                it.deadLine,
                it.isDone
            )
        }

    override suspend fun findFromLectureId(id: Int): List<Task> =
        dao.findFromLectureId(id).map {
            Task.reconstruct(
                it.id!!,
                it.lectureId,
                it.name,
                it.description,
                it.deadLine,
                it.isDone
            )
        }

    override suspend fun add(task: Task) {
        require(task.id == null)
        dao.insert(
            TaskEntity(
                task.id,
                task.lectureId,
                task.name,
                task.description,
                task.deadLine,
                task.isDone
            )
        )
    }

    override suspend fun remove(task: Task) {
        require(task.id != null)
        dao.delete(
            TaskEntity(
                task.id,
                task.lectureId,
                task.name,
                task.description,
                task.deadLine,
                task.isDone
            )
        )
    }
}