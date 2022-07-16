package io.github.szcszshiro.lectref.infrastructure

import io.github.szcszshiro.lectref.app.db.ITaskDao
import io.github.szcszshiro.lectref.app.db.LectureEntity
import io.github.szcszshiro.lectref.app.db.TaskEntity
import io.github.szcszshiro.lectref.domain.lecture.Lecture
import io.github.szcszshiro.lectref.domain.task.ITaskRepository
import io.github.szcszshiro.lectref.domain.task.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject
import kotlin.jvm.Throws

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

    override suspend fun findFromId(id: Int): Task? =
        dao.findFromId(id)?.let {
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

    @Throws(IllegalArgumentException::class)
    override suspend fun update(
        id: Int,
        newName: String?,
        newDescription: String?,
        newDeadLine: LocalDateTime?,
        isDone: Boolean?
    ) {
        val target = findFromId(id)
        require(target != null)
        require(newName?.let { Task.isNameOk(it) }?: true)
        require(newDescription?.let { Task.isDescriptionOk(it) }?: true)
        dao.update(
            TaskEntity(
                id,
                target.lectureId,
                newName?: target.name,
                newDescription?: target.description,
                newDeadLine?: target.deadLine,
                isDone?: target.isDone
            )
        )
    }
}