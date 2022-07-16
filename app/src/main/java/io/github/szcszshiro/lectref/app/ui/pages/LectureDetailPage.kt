package io.github.szcszshiro.lectref.app.ui.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.szcszshiro.lectref.app.ui.organisms.ConsensusDialog
import io.github.szcszshiro.lectref.app.ui.organisms.LectureDetail
import io.github.szcszshiro.lectref.app.ui.templates.LectureDetailTemplate
import io.github.szcszshiro.lectref.presentation.LectureDetailViewModel
import kotlinx.coroutines.runBlocking

@Composable
fun LectureDetailPage(
    lectureId: Int,
    backToList: () -> Unit,
    editLecture: () -> Unit,
    editTask: (id: Int?) -> Unit,
    editReference: (id: Int?) -> Unit
) {
    val vm: LectureDetailViewModel = hiltViewModel()
    var lectureDialogIsOpen by remember {
        mutableStateOf(false)
    }
    var deleteLectureId by remember {
        mutableStateOf(-1)
    }
    var taskDialogIsOpen by remember {
        mutableStateOf(false)
    }
    var deleteTaskId by remember {
        mutableStateOf(-1)
    }
    var referenceDialogIsOpen by remember {
        mutableStateOf(false)
    }
    var deleteReferenceId by remember {
        mutableStateOf(-1)
    }

    ConsensusDialog(
        text = "Delete this Lecture Data?",
        isOpen = lectureDialogIsOpen,
        onClose = { lectureDialogIsOpen = false },
        onConfirm = {
            backToList()
            runBlocking {
                vm.deleteLecture(deleteLectureId)
            }
        },
        onDismiss = {}
    )
    ConsensusDialog(
        text = "Delete this Task Data?",
        isOpen = taskDialogIsOpen,
        onClose = { taskDialogIsOpen = false },
        onConfirm = {
            runBlocking {
                vm.deleteTask(deleteTaskId)
            }
        },
        onDismiss = {}
    )
    ConsensusDialog(
        text = "Delete this Reference Data?",
        isOpen = referenceDialogIsOpen,
        onClose = { referenceDialogIsOpen = false },
        onConfirm = {
            runBlocking {
                vm.deleteReference(deleteReferenceId)
            }
        },
        onDismiss = {}
    )

    LectureDetailTemplate(
        contents = {
            val lecture = vm.getLectureDetailLiveData(lectureId).observeAsState()
            val tasks = vm.getTasksLiveData(lectureId).observeAsState()
            val references = vm.getReferencesLiveData(lectureId).observeAsState()

            LectureDetail(
                lectureDTO = lecture.value,
                taskDTOs = tasks.value?: emptyList(),
                referenceDTOs = references.value?: emptyList(),
                onClickEditLecture = {
                    editLecture()
                },
                onClickDeleteLecture = {
                    deleteLectureId = lecture.value?.id?: -1
                    lectureDialogIsOpen = true
                },
                onClickAddTask = {
                    editTask(null)
                },
                onClickFinishTask = {
                    vm.switchTaskIsDone(it)
                },
                onClickEditTask = {
                    editTask(it)
                },
                onClickDeleteTask = {
                    deleteTaskId = it
                    taskDialogIsOpen = true
                },
                onClickAddReference = {
                    editReference(null)
                },
                onClickOpenReference = {
                    // TODO: URLオープン処理
                },
                onClickEditReference = {
                    editReference(it)
                },
                onClickDeleteReference = {
                    deleteReferenceId = it
                    referenceDialogIsOpen = true
                },
                modifier = Modifier
                    .fillMaxSize()
            )
        },
        onClickBack = backToList
    )
}