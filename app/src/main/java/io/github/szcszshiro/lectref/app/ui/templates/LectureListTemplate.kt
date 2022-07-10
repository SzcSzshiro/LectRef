package io.github.szcszshiro.lectref.app.ui.templates

import androidx.compose.material.*
import androidx.compose.runtime.Composable

@Composable
fun LectureListTemplate(
    contents: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "LectRef") }
            )
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        contents()
    }
}