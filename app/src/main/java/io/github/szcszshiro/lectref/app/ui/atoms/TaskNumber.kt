package io.github.szcszshiro.lectref.app.ui.atoms

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.round

@Composable
fun TaskNumber(
    taskNum: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
    ) {
        require(taskNum in 0..99)
        Text(
            text = "Task: $taskNum",
            maxLines = 1
        )
    }
}

@Preview
@Composable
fun TaskNumberPreview(){
    TaskNumber(
        taskNum = 90,
        onClick = {},
        modifier = Modifier.width(100.dp)
    )
}