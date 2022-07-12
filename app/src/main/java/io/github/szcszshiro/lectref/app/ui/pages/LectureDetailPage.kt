package io.github.szcszshiro.lectref.app.ui.pages

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.szcszshiro.lectref.app.ui.organisms.LectureDetail
import io.github.szcszshiro.lectref.app.ui.templates.LectureDetailTemplate
import io.github.szcszshiro.lectref.presentation.LectureDetailViewModel

@Composable
fun LectureDetailPage(
    lectureId: Int,
    backToList: () -> Unit,

) {
    val vm: LectureDetailViewModel = hiltViewModel()
    LectureDetailTemplate(
        contents = {
            val lecture = vm.getLectureDetailLiveData(lectureId).observeAsState()
            val tasks = vm.getTasksLiveData(lectureId).observeAsState()
            val references = vm.getReferencesLiveData(lectureId).observeAsState()

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("LectRef") }
                    )
                }
            ) {
                LectureDetail(
                    lectureDTO = lecture.value,
                    taskDTOs = tasks.value?: emptyList(),
                    referenceDTOs = references.value?: emptyList(),
                    onClickEditLecture = {},
                    onClickDeleteLecture = {},
                    onClickAddTask = {},
                    onClickFinishTask = {},
                    onClickEditTask = {},
                    onClickDeleteTask = {},
                    onClickAddReference = {},
                    onClickOpenReference = {},
                    onClickEditReference = {},
                    onClickDeleteReference = {}
                )
            }
        },
        onClickBack = backToList
    )
}