package io.github.szcszshiro.lectref.domain.lecture

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek
import java.time.LocalTime

@Entity
class Lecture(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val week: DayOfWeek,
    @ColumnInfo(name = "start_time")
    val startTime: LocalTime,
    val references: List<Reference>,
    @ColumnInfo(name = "task_ids")
    val taskIds: List<Int>
){
    companion object{
        fun reconstruct(
            id: Int,
            name: String,
            description: String,
            week: DayOfWeek,
            startTime: LocalTime,
            references: List<Reference>,
            taskIds: List<Int>
        ) = Lecture(id, name, description, week, startTime, references, taskIds)
    }

}