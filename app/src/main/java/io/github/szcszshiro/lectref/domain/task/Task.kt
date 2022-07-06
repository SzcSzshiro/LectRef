package io.github.szcszshiro.lectref.domain.task

import io.github.szcszshiro.lectref.domain.lecture.Lecture
import java.time.LocalDateTime

class Task private constructor(
    val id: Int,
    val lectureId: Int,
    val name: String,
    val description: String,
    val deadLine: LocalDateTime,
    val isDone: Boolean
){
    companion object{
        fun reconstruct(
            id: Int,
            lectureId: Int,
            name: String,
            description: String,
            deadLine: LocalDateTime,
            isDone: Boolean
        ) = Task(id, lectureId, name, description, deadLine, isDone)

        fun create(
            id: Int,
            lectureId: Int,
            name: String,
            description: String,
            deadLine: LocalDateTime,
            isDone: Boolean
        ): Task?{
            if (!Lecture.isNameOk(name) || !Lecture.isDescriptionOk(description)){
                return null
            }
            return Task(id, lectureId, name, description, deadLine, isDone)
        }

        fun isNameOk(name: String) =
            name.length in 3..15
        fun isDescriptionOk(description: String) =
            description.length in 0..50
    }


}