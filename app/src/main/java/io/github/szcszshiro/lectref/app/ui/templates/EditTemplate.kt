package io.github.szcszshiro.lectref.app.ui.templates

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun EditTemplate(
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LectRef")
                }
            )
        }
    ) {
        content()
    }
}