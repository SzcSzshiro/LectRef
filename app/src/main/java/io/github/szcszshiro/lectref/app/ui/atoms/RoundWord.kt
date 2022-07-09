package io.github.szcszshiro.lectref.app.ui.atoms

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max

@Composable
fun RoundWord(
    word: String,
    color: Color,
    modifier: Modifier = Modifier.size(100.dp)
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Canvas(modifier = Modifier.fillMaxSize()){
            drawCircle(
                color = color,
                center = Offset(x = size.width / 2, y = size.height / 2),
                radius = size.minDimension/2
            )
        }
        Text(
            text = word,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun RoundWordPreview() {
    RoundWord(word = "木", Color.Blue)
}