package io.github.szcszshiro.lectref.domain.lecture

import java.time.DayOfWeek
import java.time.LocalTime

class Lecture private constructor(
    val id: Int?,
    val name: String,
    val description: String,
    val week: DayOfWeek,
    val startTime: LocalTime
){
    companion object{
        fun reconstruct(
            id: Int,
            name: String,
            description: String,
            week: DayOfWeek,
            startTime: LocalTime
        ) = Lecture(id, name, description, week, startTime)

        fun create(
            name: String,
            description: String,
            week: DayOfWeek,
            startTime: LocalTime
        ): Lecture?{
            if (!isNameOk(name) || !isDescriptionOk(description)){
                return null
            }
            return Lecture(null, name, description, week, startTime)
        }

        fun isNameOk(name: String) =
            name.length in 3..20
        fun isDescriptionOk(description: String) =
            description.length in 0..50
    }
}