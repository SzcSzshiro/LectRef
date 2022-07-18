package io.github.szcszshiro.lectref.app.ui.pages

import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.szcszshiro.lectref.app.ui.organisms.ConsensusDialog
import io.github.szcszshiro.lectref.app.ui.organisms.ReferenceEdit
import io.github.szcszshiro.lectref.app.ui.templates.EditTemplate
import io.github.szcszshiro.lectref.presentation.EditReferenceViewModel
import io.github.szcszshiro.lectref.presentation.EditTaskViewModel
import io.github.szcszshiro.lectref.usecase.ObserveReferenceUseCase
import io.github.szcszshiro.lectref.usecase.ObserveTaskUseCase
import kotlinx.coroutines.runBlocking

@Composable
fun EditReferencePage(
    referenceId: Int?,
    lectureId: Int,
    onBack: () -> Unit
) {
    val vm: EditReferenceViewModel = hiltViewModel()
    val context = LocalContext.current
    val target: ObserveReferenceUseCase.ReferenceDTO?
    runBlocking {
        target = vm.getReferenceDTO(referenceId?: -1)
    }

    var name by remember {
        mutableStateOf(target?.name?: "")
    }
    var description by remember {
        mutableStateOf(target?.description?: "")
    }
    var url by remember {
        mutableStateOf(target?.url?: "")
    }
    var showDialog by remember {
        mutableStateOf(false)
    }

    ConsensusDialog(
        text = "Cancel this Editing?",
        isOpen = showDialog,
        onClose = {
            showDialog = false
        },
        onConfirm = {
            onBack()
        },
        onDismiss = {}
    )

    EditTemplate {
        ReferenceEdit(
            name = name,
            description = description,
            url = url,
            onChangeName = { name = it },
            onChangeDescription = { description = it },
            onChangeUrl = { url = it },
            onPushCancel = {
                showDialog = true
            },
            onPushOk = {
                if (!vm.checkIsNameOk(name)) {
                    Toast.makeText(context, "Invalid Name", Toast.LENGTH_SHORT).show()
                    return@ReferenceEdit
                }
                if (!vm.checkIsDescriptionOk(description)) {
                    Toast.makeText(context, "Invalid Description", Toast.LENGTH_SHORT).show()
                    return@ReferenceEdit
                }
                onBack()
                if (referenceId == null || target == null){
                    vm.addReference(
                        lectureId, name, description, url
                    )
                }else{
                    vm.editReference(
                        referenceId, name, description, url
                    )
                }
            }
        )
    }
}