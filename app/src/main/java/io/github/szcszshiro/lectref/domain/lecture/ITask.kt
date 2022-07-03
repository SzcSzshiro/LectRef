package io.github.szcszshiro.lectref.domain.lecture

interface ITask {
    val name: String
    val description: String
    val isEnd: Boolean
}