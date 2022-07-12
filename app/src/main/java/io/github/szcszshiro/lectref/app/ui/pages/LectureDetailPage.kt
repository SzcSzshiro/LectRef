package io.github.szcszshiro.lectref.app.ui.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
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
                    // TODO: 警告画面表示
                    backToList()
                    runBlocking {
                        lecture.value?.let {
                            vm.deleteLecture(it.id)
                        }
                    }
                },
                onClickAddTask = {
                    editTask(null)
                },
                onClickFinishTask = {
                    runBlocking {

                    }
                },
                onClickEditTask = {
                    editTask(it)
                },
                onClickDeleteTask = {
                    // TODO: 警告画面表示
                    runBlocking {
                        vm.deleteTask(it)
                    }
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
                    // TODO: 警告画面表示
                    runBlocking {
                        vm.deleteReference(it)
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
            )
        },
        onClickBack = backToList
    )
}