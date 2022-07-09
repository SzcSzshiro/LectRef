package io.github.szcszshiro.lectref.app.ui.molecules

import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.szcszshiro.lectref.app.ui.atoms.RoundWord
import java.time.LocalTime

@Composable
fun WeekAndTime(
    weekWord: String,
    weekColor: Color,
    time: LocalTime,
    height: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = CenterVertically,
        modifier = modifier
    ){
        RoundWord(
            word = weekWord,
            color = weekColor,
            modifier = Modifier.size((height*0.95).dp)
        )
        val timeText = "${time.hour}時${time.minute}分～"
        Text(
            text = timeText,
            fontSize = with(LocalDensity.current) { height.dp.toSp() },
            modifier = Modifier
                .height((height*1.15).dp)
                .offset(y = (-height*0.15).dp)
                .padding(start = 10.dp, end = 10.dp)
        )
    }
}

@Preview(device = Devices.PIXEL_3)
@Composable
fun WeekAndTimePreview() {
    WeekAndTime(
        weekWord = "木",
        weekColor = Color.Gray,
        time = LocalTime.now(),
        50
    )
}