package io.github.szcszshiro.lectref.domain.lecture

interface IReference{
    val name: String
    val url: String
    val description: String
}

class Reference(
    override val name: String,
    override val url: String,
    override val description: String
): IReference