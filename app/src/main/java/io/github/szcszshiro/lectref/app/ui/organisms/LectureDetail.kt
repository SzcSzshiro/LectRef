package io.github.szcszshiro.lectref.app.ui.organisms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.szcszshiro.lectref.app.ui.molecules.LectureData
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
    onClickOpenReference: (url: String) -> Unit,
    onClickEditReference: (referenceId: Int) -> Unit,
    onClickDeleteReference: (referenceId: Int) -> Unit
) {
    if (lectureDTO == null){
        Text(text = "Loading")
        return
    }
    LazyColumn(
        modifier = modifier.padding(8.dp)
    ){
        item {
            LectureData(
                name = lectureDTO.name,
                description = lectureDTO.description,
                weekValue = lectureDTO.week.name,
                startTimeValue = "${lectureDTO.startTime.hour}:${lectureDTO.startTime.minute.toString().padStart(2, '0')}~",
                onPushEdit = onClickEditLecture,
                onPushDelete = onClickDeleteLecture
            )
        }
        item {
            Spacer(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Gray)
                    .height((0.8).dp)
                    .fillMaxWidth()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()

            ) {
                Text(
                    text = "Task",
                    fontSize = 30.sp,
                    modifier = Modifier.weight(1f)
                )
                FloatingActionButton(
                    onClick = onClickAddTask,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Push to Add Task."
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Gray)
                    .height((0.8).dp)
                    .fillMaxWidth()
            )
        }
        items(taskDTOs){ taskDTO ->
            TaskCard(
                name = taskDTO.name,
                description = taskDTO.description,
                deadLineValue = "~${taskDTO.deadLine.monthValue}/${taskDTO.deadLine.dayOfMonth} ${taskDTO.deadLine.hour}:${taskDTO.deadLine.minute.toString().padStart(2, '0')}",
                isDone = taskDTO.isDone,
                onClickFinish = {
                    onClickFinishTask(taskDTO.id)                
                },
                onClickEdit = { 
                    onClickEditTask(taskDTO.id)
                },
                onClickDelete = {
                    onClickDeleteTask(taskDTO.id)
                }
            )
        }
        item {
            Spacer(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Gray)
                    .height((0.8).dp)
                    .fillMaxWidth()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = "Reference",
                    fontSize = 30.sp,
                    modifier = Modifier.weight(1f)
                )
                FloatingActionButton(
                    onClick = onClickAddReference,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Push to Add Reference."
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Gray)
                    .height((0.8).dp)
                    .fillMaxWidth()
            )
        }
        items(referenceDTOs){ referenceDTO ->
            ReferenceCard(
                name = referenceDTO.name,
                description = referenceDTO.description,
                url = referenceDTO.url,
                onClickOpen = {
                    onClickOpenReference(referenceDTO.url)
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