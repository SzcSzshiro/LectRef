package io.github.szcszshiro.lectref.app.ui.templates

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun LectureDetailTemplate(
    contents: @Composable () -> Unit,
    onClickBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LectRef") },
                navigationIcon = {
                    IconButton(onClick = onClickBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Push to Back to List."
                        )
                    }
                }
            )
        }
    ) {
        contents()
    }
}