package io.github.szcszshiro.lectref.app.ui.organisms

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import io.github.szcszshiro.lectref.usecase.ObserveLectureUseCase
import java.time.DayOfWeek
import java.time.LocalTime

@Composable
fun LectureEdit(
    modifier: Modifier = Modifier,
    name: String,
    description: String,
    week: DayOfWeek,
    startTime: LocalTime,
    onChangeName: (value: String) -> Unit,
    onChangeDescription: (value: String) -> Unit,
    onChangeWeek: (value: DayOfWeek) -> Unit,
    onChangeStartTime: (value: LocalTime) -> Unit,
    onPushCancel: () -> Unit,
    onPushOk: () -> Unit
) {
    var weekMenuExpanded by remember {
        mutableStateOf(false)
    }
    val dialogState = rememberMaterialDialogState()

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        timepicker(
            is24HourClock = true
        ){
            onChangeStartTime(it)
        }
    }
    Column(modifier = modifier) {
        TextField(
            value = name,
            onValueChange = onChangeName,
            label = {
                Text(text = "Lecture Name")
            }
        )
        TextField(
            value = description,
            onValueChange = onChangeDescription,
            label = {
                Text(text = "Lecture Description")
            }
        )
        Text(
            text = week.name,
            fontSize = 20.sp,
            modifier = Modifier.clickable {
                weekMenuExpanded = true
            }
        )
        DropdownMenu(
            expanded = weekMenuExpanded,
            onDismissRequest = { weekMenuExpanded = false }
        ) {
            for (wi in DayOfWeek.values()){
                TextButton(onClick = {
                    onChangeWeek(wi)
                    weekMenuExpanded = false
                }) {
                    Text(
                        text = wi.name
                    )
                }
            }
        }
        Text(
            text = "${startTime.hour}:${startTime.minute.toString().padStart(2, '0')}~",
            fontSize = 20.sp,
            modifier = Modifier.clickable {
                dialogState.show()
            }
        )
        Row {
            TextButton(onClick = onPushCancel) {
                Text(text = "Cancel")
            }
            Button(
                onClick = onPushOk,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(text = "Ok")
            }
        }
    }
}

@Preview
@Composable
fun LectureEditPreview() {
    var name by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    var week by remember {
        mutableStateOf(DayOfWeek.MONDAY)
    }
    var startTime by remember {
        mutableStateOf(LocalTime.now())
    }

    LectureEdit(
        name = name,
        description = description,
        week = week,
        startTime = startTime,
        onChangeName = {name = it},
        onChangeDescription = {description = it},
        onChangeWeek = {week = it},
        onChangeStartTime = {startTime = it},
        onPushCancel = {},
        onPushOk = {}
    )
}