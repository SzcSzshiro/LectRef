package io.github.szcszshiro.lectref.app.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

@Entity(tableName = "lecture")
@TypeConverters(
    WeekConverter::class,
    LocalTimeConverter::class
)
data class LectureEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String,
    val description: String,
    val week: DayOfWeek,
    @ColumnInfo(name = "start_time")
    val startTime: LocalTime
)

@Entity(tableName = "reference")
@TypeConverters(
    StringListConverter::class
)
data class ReferenceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "lecture_id")
    val lectureId: Int,
    val name: String,
    val urls: List<String>,
    val description: String
)

@Entity(tableName = "task")
@TypeConverters(
    LocalDateTimeConverter::class
)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "lecture_id")
    val lectureId: Int,
    val name: String,
    val description: String,
    @ColumnInfo(name = "dead_life")
    val deadLine: LocalDateTime,
    @ColumnInfo(name = "is_done")
    val isDone: Boolean
)