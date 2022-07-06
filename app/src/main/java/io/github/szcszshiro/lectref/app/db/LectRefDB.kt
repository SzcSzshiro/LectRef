package io.github.szcszshiro.lectref.app.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Database(
    entities = [LectureEntity::class, TaskEntity::class, ReferenceEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LectRefDB: RoomDatabase(){
    abstract fun getLectureDao(): ILectureDao
    abstract fun getReferenceDao(): IReferenceDao
    abstract fun getTaskDao(): ITaskDao
}

@Dao
interface ILectureDao{
    @Query("SELECT * FROM lecture")
    fun allAsFlow(): Flow<List<LectureEntity>>

    @Query("SELECT * FROM lecture")
    fun findAll(): List<LectureEntity>

    @Query("SELECT * FROM lecture WHERE id == :id")
    fun findFromId(id: Int): LectureEntity?

    @Insert
    fun insert(lecture: LectureEntity)

    @Delete
    fun delete(lecture: LectureEntity)

    @Update
    fun update(lecture: LectureEntity)
}

@Dao
interface IReferenceDao{
    @Query("SELECT * FROM reference")
    fun allAsFlow(): Flow<List<ReferenceEntity>>

    @Query("SELECT * FROM reference")
    fun findAll(): List<ReferenceEntity>

    @Query("SELECT * FROM reference WHERE lecture_id == :id")
    fun findFromLectureId(id: Int): List<ReferenceEntity>

    @Insert
    fun insert(reference: ReferenceEntity)

    @Delete
    fun delete(reference: ReferenceEntity)

    @Update
    fun update(reference: ReferenceEntity)
}

@Dao
interface ITaskDao{
    @Query("SELECT * FROM task")
    fun allAsFlow(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task")
    fun findAll(): List<TaskEntity>

    @Query("SELECT * FROM task WHERE lecture_id == :id")
    fun findFromLectureId(id: Int): List<TaskEntity>

    @Insert
    fun insert(task: TaskEntity)

    @Delete
    fun delete(task: TaskEntity)

    @Update
    fun update(task: TaskEntity)
}