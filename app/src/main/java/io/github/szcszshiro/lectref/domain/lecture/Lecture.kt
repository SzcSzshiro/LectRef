package io.github.szcszshiro.lectref.domain.lecture

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek
import java.time.LocalTime

@Entity
class Lecture(
    @PrimaryKey
    val name: String,
    val week: DayOfWeek,
    val startTime: LocalTime,
    val tasks: List<ITask>,
    val references: List<IReference>
){

}