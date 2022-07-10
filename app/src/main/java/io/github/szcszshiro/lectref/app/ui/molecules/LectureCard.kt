package io.github.szcszshiro.lectref.app.ui.molecules

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalTime

@Composable
fun LectureCard(
    lectureName: String,
    taskNum: Int,
    week: String,
    startTime: LocalTime,
    onClickCard: () -> Unit,
    onClickTask: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = 2.dp,
        modifier = modifier
    ) {
        Column(modifier = Modifier.clickable { onClickCard() }) {
            Text(
                text = lectureName,
                fontSize = 25.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .fillMaxWidth()
            ) {
                TextButton(
                    onClick = onClickTask,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Task: $taskNum")
                }
                Text(
                    text = week,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .weight(1f)
                )
                Text(
                    text = "${startTime.hour}:${startTime.minute}~",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview
@Composable
fun LectureCardPreview() {
    LectureCard(
        lectureName = "Test Lecture",
        taskNum = 1,
        week = "Mon",
        startTime = LocalTime.now(),
        onClickCard = {},
        onClickTask = {},
        modifier = Modifier
            .width(300.dp)
            .wrapContentHeight()
    )
}