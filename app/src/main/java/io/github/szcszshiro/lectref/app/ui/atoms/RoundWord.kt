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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*

@Composable
fun RoundWord(
    word: String,
    color: Color,
    size: Int,
    wordColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(size.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()){
            drawCircle(
                color = color,
                center = Offset(x = this.size.width / 2, y = this.size.height / 2),
                radius = this.size.minDimension/2
            )
        }
        Text(
            text = word,
            color = wordColor,
            fontSize = with(LocalDensity.current) { (size*0.6).dp.toSp() }
        )
    }
}

@Preview
@Composable
fun RoundWordPreview() {
    RoundWord(word = "木", Color.Blue, 50, Color.White)
}