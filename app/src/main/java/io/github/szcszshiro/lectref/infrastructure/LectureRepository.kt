package io.github.szcszshiro.lectref.infrastructure

import io.github.szcszshiro.lectref.app.db.ILectureDao
import io.github.szcszshiro.lectref.app.db.LectureEntity
import io.github.szcszshiro.lectref.domain.lecture.ILectureRepository
import io.github.szcszshiro.lectref.domain.lecture.Lecture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LectureRepository @Inject constructor(
    private val dao: ILectureDao
): ILectureRepository {
    override fun getFlow(): Flow<List<Lecture>> =
        dao.allAsFlow().map { list ->
            list.map {
                Lecture.reconstruct(
                    it.id,
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
                it.id,
                it.name,
                it.description,
                it.week,
                it.startTime
            )
        }

    override suspend fun findFromId(id: Int): Lecture? =
        dao.findFromId(id)?.let {
            Lecture.reconstruct(
                it.id,
                it.name,
                it.description,
                it.week,
                it.startTime
            )
        }

    override suspend fun add(lecture: Lecture) {
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

    override suspend fun remove(lecture: Lecture) {
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

    override suspend fun update(lecture: Lecture) {
        dao.update(
            LectureEntity(
                lecture.id,
                lecture.name,
                lecture.description,
                lecture.week,
                lecture.startTime
            )
        )
    }
}