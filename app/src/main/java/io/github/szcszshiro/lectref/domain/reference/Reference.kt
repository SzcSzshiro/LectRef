package io.github.szcszshiro.lectref.domain.reference

import io.github.szcszshiro.lectref.domain.lecture.Lecture

class Reference private constructor(
    val id: Int?,
    val lectureId: Int,
    val name: String,
    val url: String,
    val description: String
){
    companion object{
        fun reconstruct(
            id: Int,
            lectureId: Int,
            name: String,
            url: String,
            description: String
        ) = Reference(id, lectureId, name, url, description)

        fun create(
            lectureId: Int,
            name: String,
            url: String,
            description: String
        ): Reference?{
            if (!isNameOk(name) || !isDescriptionOk(description)){
                return null
            }
            return Reference(null, lectureId, name, url, description)
        }

        fun isNameOk(name: String) =
            name.length in 3..30
        fun isDescriptionOk(description: String) =
            description.length in 0..50
    }
}
