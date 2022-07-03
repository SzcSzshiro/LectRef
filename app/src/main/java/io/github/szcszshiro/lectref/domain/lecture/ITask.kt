package io.github.szcszshiro.lectref.domain.lecture

import java.time.LocalDate
import java.time.LocalDateTime

interface ITask {
    val name: String
    val description: String
    val deadLine: LocalDate
    val isEnd: Boolean
}

class Task(
    override val name: String,
    override val description: String,
    override val deadLine: LocalDate,
    override val isEnd: Boolean
): ITask