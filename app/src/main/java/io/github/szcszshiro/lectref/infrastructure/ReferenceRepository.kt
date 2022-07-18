package io.github.szcszshiro.lectref.infrastructure

import io.github.szcszshiro.lectref.app.db.IReferenceDao
import io.github.szcszshiro.lectref.app.db.ReferenceEntity
import io.github.szcszshiro.lectref.app.db.TaskEntity
import io.github.szcszshiro.lectref.domain.reference.IReferenceRepository
import io.github.szcszshiro.lectref.domain.reference.Reference
import io.github.szcszshiro.lectref.domain.task.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.jvm.Throws

class ReferenceRepository @Inject constructor(
    private val dao: IReferenceDao
): IReferenceRepository {
    override fun getFlow(): Flow<List<Reference>> =
        dao.allAsFlow().map { list ->
            list.map {
                Reference.reconstruct(
                    it.id!!,
                    it.lectureId,
                    it.name,
                    it.url,
                    it.description
                )
            }
        }

    override suspend fun findAll(): List<Reference> =
        dao.findAll().map {
            Reference.reconstruct(
                it.id!!,
                it.lectureId,
                it.name,
                it.url,
                it.description
            )
        }

    override suspend fun findFromId(id: Int): Reference? =
        dao.findFromId(id)?.let {
            Reference.reconstruct(
                it.id!!,
                it.lectureId,
                it.name,
                it.url,
                it.description
            )
        }

    override suspend fun findFromLectureId(id: Int): List<Reference> =
        dao.findFromLectureId(id).map {
            Reference.reconstruct(
                it.id!!,
                it.lectureId,
                it.name,
                it.url,
                it.description
            )
        }

    override suspend fun add(reference: Reference) {
        require(reference.id == null)
        dao.insert(
            ReferenceEntity(
                reference.id,
                reference.lectureId,
                reference.name,
                reference.url,
                reference.description
            )
        )
    }

    @Throws(IllegalArgumentException::class)
    override suspend fun remove(reference: Reference) {
        require(reference.id != null)
        dao.delete(
            ReferenceEntity(
                reference.id,
                reference.lectureId,
                reference.name,
                reference.url,
                reference.description
            )
        )
    }

    @Throws(IllegalArgumentException::class)
    override suspend fun update(
        id: Int,
        newName: String?,
        newDescription: String?,
        newUrl: String?
    ) {
        val target = findFromId(id)
        require(target != null)
        require(newName?.let { Task.isNameOk(it) }?: true)
        require(newDescription?.let { Task.isDescriptionOk(it) }?: true)
        dao.update(
            ReferenceEntity(
                target.id,
                target.lectureId,
                newName?: target.name,
                newUrl?: target.url,
                newDescription?: target.description
            )
        )
    }
}