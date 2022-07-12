package io.github.szcszshiro.lectref.app.ui.templates

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable

@Composable
fun LectureListTemplate(
    contents: @Composable () -> Unit,
    onClickAdd: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "LectRef") },
                actions = {
                    IconButton(onClick = onClickAdd){
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Click to Add Lecture"
                        )
                    }
                }
            )
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        contents()
    }
}