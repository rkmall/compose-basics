package com.rm.compose_fundamentals.topics.t7_components.button

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FilledButtonExample(onClick: () -> Unit = {}) {
    Button(onClick = { onClick() }) {
        Text(text = "Filled")
    }
}

@Composable
fun FilledTonalButtonExample(onClick: () -> Unit = {}) {
    FilledTonalButton(onClick = { onClick() }) {
        Text(text = "Tonal")
    }
}

@Composable
fun OutlinedButtonExample(onClick: () -> Unit = {}) {
    OutlinedButton(onClick = { onClick() }) {
        Text(text = "Outlined")
    }
}

@Composable
fun ElevatedButtonExample(onClick: () -> Unit = {}) {
    ElevatedButton(
        onClick = { onClick() },
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            focusedElevation = 10.dp
        )
    ) {
        Text(text = "Elevated")
    }
}

@Composable
fun TextButtonExample(onClick: () -> Unit = {}) {
    TextButton(onClick = { onClick() }) {
        Text(text = "Text Button")
    }
}

@Composable
fun IconButtonExample() {
    var text by remember { mutableStateOf("Hello") }
    Column {
        Text(text = text)
        IconButton(onClick = { text = "Mellow" }) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Check"
            )
        }
    }
}

@Preview
@Composable
fun FilledButtonsPreview() {
    FilledButtonExample()
}

@Preview
@Composable
fun FilledTonalButtonsPreview() {
    FilledTonalButtonExample()
}

@Preview
@Composable
fun OutlinedButtonsPreview() {
    OutlinedButtonExample()
}

@Preview
@Composable
fun ElevatedButtonsPreview() {
    ElevatedButtonExample()
}

@Preview
@Composable
fun TextButtonsPreview() {
    TextButtonExample()
}

@Preview
@Composable
fun IconButtonsPreview() {
    IconButtonExample()
}


