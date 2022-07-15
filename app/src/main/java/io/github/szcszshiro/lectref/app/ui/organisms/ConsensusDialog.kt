package io.github.szcszshiro.lectref.app.ui.organisms

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.runBlocking

@Composable
fun ConsensusDialog(
    modifier: Modifier = Modifier,
    text: String,
    isOpen: Boolean,
    onClose: () -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (isOpen){
        AlertDialog(
            onDismissRequest = {
                onClose()
            },
            confirmButton = {
                Button(onClick = {
                    onClose()
                    onConfirm()
                }) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    onClose()
                    onDismiss()
                }) {
                    Text(text = "CANCEL")
                }
            },
            text = {
                Text(text = text)
            },
            modifier = modifier
        )
    }
}