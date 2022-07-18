package io.github.szcszshiro.lectref.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.szcszshiro.lectref.usecase.ObserveReferenceUseCase
import io.github.szcszshiro.lectref.usecase.RecordReferenceUseCase
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class EditReferenceViewModel @Inject constructor(
    private val observeReferenceUseCase: ObserveReferenceUseCase,
    private val recordReferenceUseCase: RecordReferenceUseCase
): ViewModel() {
    suspend fun getReferenceDTO(id: Int) =
        observeReferenceUseCase
            .findFromId(id)
            ?.let {
                ObserveReferenceUseCase.ReferenceDTO(
                    it.id!!,
                    it.lectureId,
                    it.name,
                    it.url,
                    it.description
                )
            }

    fun addReference(
        lectureId: Int,
        name: String,
        description: String,
        url: String
    ){
        recordReferenceUseCase.addReference(lectureId, name, url, description)
    }

    fun editReference(
        id: Int,
        name: String?,
        description: String?,
        url: String
    ){
        recordReferenceUseCase.editTask(id, name, description, url)
    }

    fun checkIsNameOk(newName: String) = recordReferenceUseCase.checkIsNameOk(newName)

    fun checkIsDescriptionOk(newDescription: String) = recordReferenceUseCase.checkIsDescriptionOk(newDescription)
}