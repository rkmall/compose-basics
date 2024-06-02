package com.rm.compose_fundamentals.topics.t7_components.snackbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Preview
@Composable
fun SnackBarsPreview() {

    SnackBarExample()
}

@Composable
fun SnackBarExample() {
    /**
     * Variable that provides access to Snackbar properties
     * and functions to display Snackbar.
     * However, the provided functions are suspend functions,
     * therefore a CoroutineScope is required to invoke them.
     */
    val snackbarHostState = remember { SnackbarHostState() }

    // CoroutineScope to invoke snackbarHostState functions
    val scope = rememberCoroutineScope()

    var text: String by remember { mutableStateOf("Hello") }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "This is snackbar",
                            actionLabel = "OK",
                            duration = SnackbarDuration.Short
                        )

                        text = when (result) {
                            SnackbarResult.ActionPerformed -> "Snackbar action performed"
                            SnackbarResult.Dismissed -> "Snackbar action dismissed"
                        }
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Info ,
                    contentDescription = "Info"
                )

                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = "Show Snackbar"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                text = text,
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize
            )
        }
    }
}
