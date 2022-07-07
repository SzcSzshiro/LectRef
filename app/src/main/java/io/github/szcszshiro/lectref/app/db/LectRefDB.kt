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
    suspend fun findAll(): List<LectureEntity>

    @Query("SELECT * FROM lecture WHERE id == :id")
    suspend fun findFromId(id: Int): LectureEntity?

    @Insert
    suspend fun insert(lecture: LectureEntity)

    @Delete
    suspend fun delete(lecture: LectureEntity)

    @Update
    suspend fun update(lecture: LectureEntity)
}

@Dao
interface IReferenceDao{
    @Query("SELECT * FROM reference")
    fun allAsFlow(): Flow<List<ReferenceEntity>>

    @Query("SELECT * FROM reference")
    suspend fun findAll(): List<ReferenceEntity>

    @Query("SELECT * FROM reference WHERE id == :id")
    suspend fun findFromId(id: Int): ReferenceEntity?

    @Query("SELECT * FROM reference WHERE lecture_id == :id")
    suspend fun findFromLectureId(id: Int): List<ReferenceEntity>

    @Insert
    suspend fun insert(reference: ReferenceEntity)

    @Delete
    suspend fun delete(reference: ReferenceEntity)

    @Update
    suspend fun update(reference: ReferenceEntity)
}

@Dao
interface ITaskDao{
    @Query("SELECT * FROM task")
    fun allAsFlow(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task")
    suspend fun findAll(): List<TaskEntity>

    @Query("SELECT * FROM task WHERE id == :id")
    suspend fun findFromId(id: Int): TaskEntity?

    @Query("SELECT * FROM task WHERE lecture_id == :id")
    suspend fun findFromLectureId(id: Int): List<TaskEntity>

    @Insert
    suspend fun insert(task: TaskEntity)

    @Delete
    suspend fun delete(task: TaskEntity)

    @Update
    suspend fun update(task: TaskEntity)
}