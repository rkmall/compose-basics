package com.rm.compose_fundamentals.topics.t7_components.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Preview
@Composable
fun TextFieldPreview() {
    TextFieldExample()
}

@Preview
@Composable
fun TextFieldSnackbarPreview() {
    TextFieldSnackbar()
}

@Composable
fun TextFieldExample() {
    var text by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = text,
        onValueChange = { text = it },
        prefix = {  Text(text = "Text: ") },
        textStyle = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun TextFieldSnackbar() {
    var text by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)  }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                value = text,
                label = {
                    Text(text = "Enter your name")
                },
                onValueChange = { newText -> text = newText },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.End),
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = text,
                            actionLabel = "Done",
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            ) {
                Text(text = "Click me")
            }
        }
    }
}
