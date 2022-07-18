package io.github.szcszshiro.lectref.app.ui.organisms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReferenceEdit(
    modifier: Modifier = Modifier,
    name: String,
    description: String,
    url: String,
    onChangeName: (value: String) -> Unit,
    onChangeDescription: (value: String) -> Unit,
    onChangeUrl: (value: String) -> Unit,
    onPushCancel: () -> Unit,
    onPushOk: () -> Unit
) {
    Column(modifier = modifier) {
        TextField(
            value = name,
            onValueChange = onChangeName,
            label = {
                Text(text = "Reference Name")
            }
        )
        TextField(
            value = description,
            onValueChange = onChangeDescription,
            label = {
                Text(text = "Reference description")
            }
        )
        TextField(
            value = url,
            onValueChange = onChangeUrl,
            label = {
                Text(text = "URL")
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