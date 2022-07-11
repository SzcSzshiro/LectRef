package io.github.szcszshiro.lectref.app.ui.molecules

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDateTime

@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    name: String,
    description: String,
    deadLineValue: String,
    isDone: Boolean,
    onClickFinish: () -> Unit,
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit
) {
    var isExpanded by remember {
        mutableStateOf(true)
    }
    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable {
                isExpanded = !isExpanded
            }
            .animateContentSize()

    ) {
        Column(modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .padding(8.dp)) {
            Text(
                text = name,
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
                Text(
                    text = deadLineValue,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .weight(1f)
                )
                Button(
                    onClick = { onClickFinish() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (isDone) MaterialTheme.colors.secondary else MaterialTheme.colors.primary
                    ),
                    modifier = Modifier.wrapContentWidth()
                ) {
                    Text(
                        text = "Finish",
                        fontSize = 15.sp
                    )
                }
            }
            if (isExpanded){
                Text(
                    text = description,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onClickEdit) {
                        Text(text = "Edit")
                    }
                    TextButton(
                        onClick = onClickDelete,
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                    ) {
                        Text(text = "Delete")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TaskCardPreview() {
    var isDone by remember {
        mutableStateOf(false)
    }

    TaskCard(
        name = "Test Task",
        description = "This is Test",
        deadLineValue = "~4/1 12:30",
        isDone = isDone,
        onClickFinish = { isDone = !isDone},
        onClickEdit = {},
        onClickDelete = {}
    )
}