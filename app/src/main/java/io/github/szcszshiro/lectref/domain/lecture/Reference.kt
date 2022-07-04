package io.github.szcszshiro.lectref.domain.lecture

data class Reference(
    val name: String,
    val urls: List<String>,
    val description: String
)