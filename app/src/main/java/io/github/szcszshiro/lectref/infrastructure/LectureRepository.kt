package io.github.szcszshiro.lectref.infrastructure

import io.github.szcszshiro.lectref.app.db.ILectureDao
import io.github.szcszshiro.lectref.app.db.LectureEntity
import io.github.szcszshiro.lectref.domain.lecture.ILectureRepository
import io.github.szcszshiro.lectref.domain.lecture.Lecture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.DayOfWeek
import java.time.LocalTime
import javax.inject.Inject
import kotlin.jvm.Throws

class LectureRepository @Inject constructor(
    private val dao: ILectureDao
): ILectureRepository {
    override fun getFlow(): Flow<List<Lecture>> =
        dao.allAsFlow().map { list ->
            list.map {
                Lecture.reconstruct(
                    it.id!!,
                    it.name,
                    it.description,
                    it.week,
                    it.startTime
                )
            }
        }

    override suspend fun findAll(): List<Lecture> =
        dao.findAll().map {
            Lecture.reconstruct(
                it.id!!,
                it.name,
                it.description,
                it.week,
                it.startTime
            )
        }

    override suspend fun findFromId(id: Int): Lecture? =
        dao.findFromId(id)?.let {
            Lecture.reconstruct(
                it.id!!,
                it.name,
                it.description,
                it.week,
                it.startTime
            )
        }

    override suspend fun add(lecture: Lecture) {
        require(lecture.id == null)
        dao.insert(
            LectureEntity(
                lecture.id,
                lecture.name,
                lecture.description,
                lecture.week,
                lecture.startTime
            )
        )
    }

    @Throws(IllegalArgumentException::class)
    override suspend fun remove(lecture: Lecture) {
        require(lecture.id != null)
        dao.delete(
            LectureEntity(
                lecture.id,
                lecture.name,
                lecture.description,
                lecture.week,
                lecture.startTime
            )
        )
    }

    @Throws(IllegalArgumentException::class)
    override suspend fun update(
        id: Int,
        newName: String?,
        newDescription: String?,
        newWeek: DayOfWeek?,
        newStartTime: LocalTime?
    ) {
        val target = findFromId(id)
        require(target != null)
        require(newName?.let { Lecture.isNameOk(it) }?: true)
        require(newDescription?.let { Lecture.isDescriptionOk(it) }?: true)
        dao.update(
            LectureEntity(
                id,
                newName?: target.name,
                newDescription?: target.description,
                newWeek?: target.week,
                newStartTime?: target.startTime
            )
        )
    }
}