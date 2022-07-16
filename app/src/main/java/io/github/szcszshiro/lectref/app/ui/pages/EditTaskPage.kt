package io.github.szcszshiro.lectref.app.ui.pages

import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.szcszshiro.lectref.app.ui.organisms.ConsensusDialog
import io.github.szcszshiro.lectref.app.ui.organisms.TaskEdit
import io.github.szcszshiro.lectref.app.ui.templates.EditTemplate
import io.github.szcszshiro.lectref.presentation.EditTaskViewModel
import io.github.szcszshiro.lectref.usecase.ObserveTaskUseCase
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

@Composable
fun EditTaskPage(
    taskId: Int?,
    lectureId: Int,
    onBack: () -> Unit
) {
    val vm: EditTaskViewModel = hiltViewModel()
    val context = LocalContext.current
    val target: ObserveTaskUseCase.TaskDTO?
    runBlocking {
        target  = vm.getTaskDTO(taskId?: -1)
    }

    var name by remember {
        mutableStateOf(target?.name?: "")
    }
    var description by remember {
        mutableStateOf(target?.description?: "")
    }
    var deadLine by remember {
        mutableStateOf(target?.deadLine?: LocalDateTime.now())
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
        TaskEdit(
            name = name,
            description = description,
            deadLine = deadLine,
            onChangeName = {name = it},
            onChangeDescription = {description = it},
            onChangeDeadLine = {deadLine = it},
            onPushCancel = { showDialog = true },
            onPushOk = {
                if (!vm.checkIsNameOk(name)) {
                    Toast.makeText(context, "Invalid Name", Toast.LENGTH_SHORT).show()
                    return@TaskEdit
                }
                if (!vm.checkIsDescriptionOk(description)) {
                    Toast.makeText(context, "Invalid Description", Toast.LENGTH_SHORT).show()
                    return@TaskEdit
                }
                onBack()
                if (taskId == null || target == null){
                    vm.addTask(
                        lectureId, name, description, deadLine
                    )
                }else{
                    vm.editTask(
                        taskId, name, description, deadLine, target.isDone
                    )
                }
            }
        )
    }

}