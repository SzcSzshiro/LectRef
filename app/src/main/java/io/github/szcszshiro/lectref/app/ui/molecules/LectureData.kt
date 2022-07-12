package io.github.szcszshiro.lectref.app.ui.molecules

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
import java.time.DayOfWeek
import java.time.LocalTime

@Composable
fun LectureData(
    name: String,
    description: String,
    weekValue: String,
    startTimeValue: String,
    onPushEdit: () -> Unit,
    onPushDelete: () -> Unit,
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
                .height((0.8).dp)
                .fillMaxWidth()
        )
        Text(
            text = description,
            fontSize = 18.sp
        )
        Row(Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
        ) {
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
                onClick = { onPushEdit() },
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
                onClick = { onPushDelete() },
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
fun LectureDataPreview() {
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
        LectureData(
            name = "Sample Lecture",
            description = "This is Sample.",
            weekValue = DayOfWeek.MONDAY.name,
            startTimeValue = "${now.hour}:${now.minute}~",
            onPushEdit = {},
            onPushDelete = {}
        )
    }
}