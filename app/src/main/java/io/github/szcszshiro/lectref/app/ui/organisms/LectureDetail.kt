package io.github.szcszshiro.lectref.app.ui.organisms

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.szcszshiro.lectref.app.ui.molecules.ReferenceCard
import io.github.szcszshiro.lectref.app.ui.molecules.TaskCard
import io.github.szcszshiro.lectref.usecase.ObserveLectureUseCase
import io.github.szcszshiro.lectref.usecase.ObserveReferenceUseCase
import io.github.szcszshiro.lectref.usecase.ObserveTaskUseCase

@Composable
fun LectureDetail(
    modifier: Modifier = Modifier,
    lectureDTO: ObserveLectureUseCase.LectureDTO?,
    taskDTOs: List<ObserveTaskUseCase.TaskDTO>,
    referenceDTOs: List<ObserveReferenceUseCase.ReferenceDTO>,
    onClickEditLecture: () -> Unit,
    onClickDeleteLecture: () -> Unit,
    onClickAddTask: () -> Unit,
    onClickFinishTask: (taskId: Int) -> Unit,
    onClickEditTask: (taskId: Int) -> Unit,
    onClickDeleteTask: (taskId: Int) -> Unit,
    onClickAddReference: () -> Unit,
    onClickOpenReference: (referenceId: Int) -> Unit,
    onClickEditReference: (referenceId: Int) -> Unit,
    onClickDeleteReference: (referenceId: Int) -> Unit
) {
    if (lectureDTO == null){
        Text(text = "読み込み中")
        return
    }
    LazyColumn(
        modifier = modifier.padding(8.dp)
    ){
        item {
            LectureData(
                name = lectureDTO.name,
                description = lectureDTO.description,
                weekValue = "${lectureDTO.week.value}",
                startTimeValue = "${lectureDTO.startTime.hour}:${lectureDTO.startTime.minute}~",
                onPushEdit = onClickEditLecture,
                onPushDelete = onClickDeleteLecture
            )
        }
        item {
            Row() {
                Text(text = "Task")
                FloatingActionButton(onClick = onClickAddTask) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Push to Add Task."
                    )
                }
            }
        }
        items(taskDTOs){ taskDTO ->
            TaskCard(
                name = taskDTO.name,
                description = taskDTO.description,
                deadLineValue = "~${taskDTO.deadLine.hour}:${taskDTO.deadLine.minute}",
                isDone = taskDTO.isDone,
                onClickFinish = {
                    onClickFinishTask(taskDTO.id)                
                },
                onClickEdit = { 
                    onClickEditTask(taskDTO.id)
                },
                onClickDelete = {
                    onClickDeleteTask(taskDTO.id)
                },
                modifier = Modifier.padding(8.dp)
            )
        }
        item {
            Row() {
                Text(text = "Reference")
                FloatingActionButton(onClick = onClickAddReference) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Push to Add Reference."
                    )
                }
            }
        }
        items(referenceDTOs){ referenceDTO ->
            ReferenceCard(
                name = referenceDTO.name,
                description = referenceDTO.description,
                url = referenceDTO.url,
                onClickOpen = {
                    onClickOpenReference(referenceDTO.id)
                },
                onClickEdit = {
                    onClickEditReference(referenceDTO.id)
                },
                onClickDelete = {
                    onClickDeleteReference(referenceDTO.id)
                }
            )
        }
    }
}