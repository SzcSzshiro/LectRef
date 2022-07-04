package io.github.szcszshiro.lectref.domain.lecture

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek
import java.time.LocalTime

@Entity
class Lecture private constructor(
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

        fun create(
            id: Int,
            name: String,
            description: String,
            week: DayOfWeek,
            startTime: LocalTime,
            references: List<Reference>,
            taskIds: List<Int>
        ): Lecture?{
            if (!isNameOk(name) || !isDescriptionOk(description)){
                return null
            }
            return Lecture(id, name, description, week, startTime, references, taskIds)
        }

        fun isNameOk(name: String) =
            name.length in 3..15
        fun isDescriptionOk(description: String) =
            description.length in 0..50
    }
}