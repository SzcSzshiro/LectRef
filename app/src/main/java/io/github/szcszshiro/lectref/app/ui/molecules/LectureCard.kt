package io.github.szcszshiro.lectref.app.ui.molecules

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.szcszshiro.lectref.app.ui.atoms.TaskNumber
import java.time.LocalTime

@Composable
fun LectureCard(
    lectureName: String,
    taskNum: Int,
    week: String,
    startTime: LocalTime,
    onClick: () -> Unit,
    onClickTask: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.DarkGray
        ),
        modifier = modifier.height(120.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()){
            Spacer(modifier = Modifier.weight(0.2f))
            Text(
                text = lectureName,
                fontSize = 29.sp,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.weight(1.5f)
            )
            Spacer(modifier = Modifier.weight(0.2f))
            Row(modifier = Modifier.weight(1.0f)) {
                TaskNumber(
                    taskNum = taskNum,
                    onClick = onClickTask,
                    modifier = Modifier.weight(3f)
                )
                Spacer(modifier = Modifier.weight(1f))
                WeekAndTime(
                    weekWord = week,
                    weekColor = Color.Blue,
                    time = startTime,
                    height = 20,
                    modifier = Modifier.weight(5f)
                )
            }
        }
    }
}

@Preview(device = Devices.PIXEL_3)
@Composable
fun LectureCardPreview() {
    LectureCard(
        lectureName = "電気電子情報系特論あいうえお",
        taskNum = 3,
        week = "月",
        startTime = LocalTime.now(),
        onClick = {},
        onClickTask = {}
    )
}