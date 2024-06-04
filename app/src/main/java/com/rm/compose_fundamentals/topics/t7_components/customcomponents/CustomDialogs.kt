package com.rm.compose_fundamentals.topics.t7_components.customcomponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog

@Preview
@Composable
fun PreviewAlertDialog() {
    AlertDialog()
}

@Composable
fun AlertDialog() {
    var showPopUp by remember { mutableStateOf(true) }
    val onPopupDismissed = { showPopUp = false }

    if (showPopUp) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = onPopupDismissed,
            title = {
                Text(text = "Alert")
            },
            text = {
                Text(text = "Please confirm")
            },
            confirmButton = {
                Button(
                    onClick = onPopupDismissed,
                ) {
                    Text(text ="Dismiss")
                }
            }
        )
    }
}

fun <T> ((T) -> Boolean).and(arg: (T) -> Boolean): (T) -> Boolean = { this(it) && arg(it) }


@Preview
@Composable
fun PreviewDeleteDialog() {
    DeleteDialog {}
}

@Composable
fun DeleteDialog(onConfirmClicked: (close: Boolean) -> Unit) {
    var showPopUp by remember { mutableStateOf(true) }
    val onPopupDismissed = {
        showPopUp = false
    }

    if (showPopUp) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = onPopupDismissed,
            title = {
                Text(text = "Delete")
            },
            text = {
                Text(text = "Please confirm")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showPopUp = false
                        onConfirmClicked(true)
                    },
                ) {
                    Text(text = "Confirm")
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewChoiceDialog() {
    ChoiceDialog(currentChoice = 1, choices = listOf("app", "desktop", "web"), onDismiss = { /*TODO*/ }) {
        
    }
}

// The choices are the display name of each item.
// The output is the index the user chose.
@Composable
fun ChoiceDialog(
    currentChoice: Int,
    choices: List<String>,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onChoose: (Int) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        RadioButtons(
            choices,
            choices[currentChoice],
            Modifier
                .fillMaxWidth()
                .then(modifier)
        ) { onChoose(it); onDismiss() }
    }
}