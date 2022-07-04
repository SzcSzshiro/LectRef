package io.github.szcszshiro.lectref.domain.lecture

class Reference private constructor(
    val name: String,
    val urls: List<String>,
    val description: String
){
    companion object{
        fun reconstruct(
            name: String,
            urls: List<String>,
            description: String
        ) = Reference(name, urls, description)

        fun create(
            name: String,
            urls: List<String>,
            description: String
        ): Reference?{
            if (!Lecture.isNameOk(name) || !Lecture.isDescriptionOk(description)){
                return null
            }
            return Reference(name, urls, description)
        }

        fun isNameOk(name: String) =
            name.length in 3..15
        fun isDescriptionOk(description: String) =
            description.length in 0..50
    }
}
