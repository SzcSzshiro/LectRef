package io.github.szcszshiro.lectref.app.ui.molecules

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonElevation
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
    weekValue: String,
    startTimeValue: String,
    onClickCard: () -> Unit,
    onClickTask: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable { onClickCard() }

    ) {
        Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp).padding(8.dp)) {
            Text(
                text = lectureName,
                fontSize = 30.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextButton(
                    onClick = onClickTask,
                    modifier = Modifier.wrapContentWidth()
                ) {
                    Text(
                        text = "Task: $taskNum",
                        fontSize = 15.sp
                    )
                }
                Text(
                    text = weekValue,
                    textAlign = TextAlign.End,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .weight(1f)
                )
                Text(
                    text = startTimeValue,
                    fontSize = 15.sp,
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
        weekValue = "Mon",
        startTimeValue = "12:30~",
        onClickCard = {},
        onClickTask = {},
        modifier = Modifier
    )
}