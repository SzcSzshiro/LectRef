package io.github.szcszshiro.lectref.domain.reference

import io.github.szcszshiro.lectref.domain.lecture.Lecture

class Reference private constructor(
    val id: Int?,
    val lectureId: Int,
    val name: String,
    val urls: List<String>,
    val description: String
){
    companion object{
        fun reconstruct(
            id: Int,
            lectureId: Int,
            name: String,
            urls: List<String>,
            description: String
        ) = Reference(id, lectureId, name, urls, description)

        fun create(
            lectureId: Int,
            name: String,
            urls: List<String>,
            description: String
        ): Reference?{
            if (!isNameOk(name) || !isDescriptionOk(description)){
                return null
            }
            return Reference(null, lectureId, name, urls, description)
        }

        fun isNameOk(name: String) =
            name.length in 3..15
        fun isDescriptionOk(description: String) =
            description.length in 0..50
    }
}
