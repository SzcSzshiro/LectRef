package io.github.szcszshiro.lectref.app.ui.organisms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.szcszshiro.lectref.presentation.LectureDetailViewModel
import io.github.szcszshiro.lectref.usecase.ObserveLectureUseCase
import io.github.szcszshiro.lectref.usecase.ObserveReferenceUseCase
import io.github.szcszshiro.lectref.usecase.ObserveTaskUseCase
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.concurrent.Flow

@Composable
fun LectureDetail(
    lectureId: Int,
    name: String,
    description: String,
    weekValue: String,
    startTimeValue: String,
    onPushEdit: (id: Int) -> Unit,
    onPushDelete: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            fontSize = 30.sp
        )
        Spacer(
            modifier = Modifier
                .padding(8.dp)
                .background(Color.Gray)
                .height((0.5).dp)
                .fillMaxWidth()
        )
        Text(
            text = description,
            fontSize = 18.sp
        )
        Row(Modifier.fillMaxWidth()) {
            Text(
                text = weekValue,
                fontSize = 18.sp
            )
            Text(
                text = startTimeValue,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            FloatingActionButton(
                onClick = { onPushEdit(lectureId) },
                modifier = Modifier
                    .padding(8.dp)
                    .size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Push to Edit the lecture information."
                )
            }
            FloatingActionButton(
                onClick = { onPushDelete(lectureId) },
                modifier = Modifier
                    .padding(8.dp)
                    .size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Push to Delete the lecture information."
                )
            }
        }
    }
}

@Preview
@Composable
fun LectureDetailPreview() {
    val now = LocalTime.now()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "LectRef"
                    )
                }
            )
        }
    ) {
        LectureDetail(
            lectureId = 1,
            name = "Sample Lecture",
            description = "This is Sample.",
            weekValue = DayOfWeek.MONDAY.name,
            startTimeValue = "${now.hour}:${now.minute}~",
            onPushEdit = {},
            onPushDelete = {}
        )
    }
}