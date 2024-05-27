package com.rm.compose_fundamentals.topics.t7_components.examples

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.rm.compose_fundamentals.R



@Preview
@Composable
fun PreviewDisplayAlertDialog() {
    DisplayAlertDialog()
}


@Composable
fun DisplayAlertDialog() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var showDialog by remember { mutableStateOf(false) }

        if (showDialog) {
            AlertDialogExample(
                onDismissRequest = { showDialog = false},
                onConfirmation = { showDialog = false},
                dialogTitle = "Confirm Deletion" ,
                dialogText = "Are you sure you want to delete this task?" ,
                icon = Icons.Default.Info ,
                iconDescription = "Delete"
            )
        }

        Button(onClick = { showDialog = true }
        ) {
            Text(text = "Show dialog")
        }
    }
}

@Preview
@Composable
fun PreviewAlertDialog() {
    AlertDialogExample(
        onDismissRequest = {},
        onConfirmation = {},
        dialogTitle = "Confirm Deletion" ,
        dialogText = "Are you sure you want to delete this task?" ,
        icon = Icons.Default.Info ,
        iconDescription = "Delete"
    )
}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
    iconDescription: String,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        title = { Text(text = dialogTitle) },
        text = { Text(text = dialogText) },
        icon = { Icon(imageVector = icon, contentDescription = iconDescription) },

        confirmButton = {
            TextButton(onClick = { onConfirmation() }) {
                Text("Confirm")
            }
        },

        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text("Cancel")
            }
        },

        onDismissRequest = { onDismissRequest() },
    )
}



@Preview
@Composable
fun PreviewDialog() {
    DialogExample(
        painter = painterResource(id = R.drawable.cupcake),
        imageDescription = "Cupcake"
    )
}


@Composable
fun DialogExample(
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
    painter: Painter,
    imageDescription: String,

    ) {
    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painter,
                    contentDescription = imageDescription,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(160.dp)
                )
                Text(
                    text = "This is a dialog with buttons and an image.",
                    modifier = Modifier.padding(16.dp),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}