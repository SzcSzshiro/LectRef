package io.github.szcszshiro.lectref.app.ui.organisms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun TaskEdit(
    modifier: Modifier = Modifier,
    name: String,
    description: String,
    deadLine: LocalDateTime,
    onChangeName: (value: String) -> Unit,
    onChangeDescription: (value: String) -> Unit,
    onChangeDeadLine: (value: LocalDateTime) -> Unit,
    onPushCancel: () -> Unit,
    onPushOk: () -> Unit
) {
    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()

    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton("OK")
            negativeButton("CANCEL")
        }
    ) {
        datepicker(
            initialDate = deadLine.toLocalDate(),
            onDateChange = {
                onChangeDeadLine(LocalDateTime.of(it, deadLine.toLocalTime()))
            }
        )
    }
    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton("OK")
            negativeButton("CANCEL")
        }
    ) {
        timepicker(
            initialTime = deadLine.toLocalTime(),
            is24HourClock = true,
            onTimeChange = {
                onChangeDeadLine(LocalDateTime.of(deadLine.toLocalDate(), it))
            }
        )
    }

    Column(modifier = modifier) {
        TextField(
            value = name,
            onValueChange = onChangeName,
            label = {
                Text(text = "Task Name")
            }
        )
        TextField(
            value = description,
            onValueChange = onChangeDescription,
            label = {
                Text(text = "Task Description")
            }
        )
        Text(text = "Task DeadLine:")
        Text(
            text = "${deadLine.toLocalDate()}",
            fontSize = 20.sp,
            modifier = Modifier.clickable {
                dateDialogState.show()
            }
        )
        Text(
            text = "${deadLine.toLocalTime()}",
            fontSize = 20.sp,
            modifier = Modifier.clickable {
                timeDialogState.show()
            }
        )
        Row {
            TextButton(onClick = onPushCancel) {
                Text(text = "CANCEL")
            }
            Button(
                onClick = onPushOk,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(text = "OK")
            }
        }
    }
}

@Preview
@Composable
fun TaskEditPreview() {
    var name by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    var deadLine by remember {
        mutableStateOf(LocalDateTime.now())
    }

    TaskEdit(
        name = name,
        description = description,
        deadLine = deadLine,
        onChangeName = { name = it },
        onChangeDescription = { description = it },
        onChangeDeadLine = { deadLine = it },
        onPushCancel = {},
        onPushOk = {}
    )
}