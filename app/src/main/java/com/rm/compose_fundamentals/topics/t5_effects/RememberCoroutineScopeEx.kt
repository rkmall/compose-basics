package com.rm.compose_fundamentals.topics.t5_effects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
fun PreviewRememberCoroutineScopeEx() {
    RememberCoroutineScopeEx()
}

@Composable
fun RememberCoroutineScopeEx() {
    var loading by remember { mutableStateOf(false) }
    var alphaValue by remember { mutableFloatStateOf(0f) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Coroutine should be only launched inside callback or LaunchedEffect
        // Otherwise the coroutine will be launched multiple times when Composable composes
        //scope.launch {}

        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }

        Text(
            modifier = Modifier
                .alpha(alphaValue),
            text = "Done",
            fontSize = 28.sp,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                loading = true
                alphaValue = 0f

                // When this button is clicked, only this coroutine will be launched
                // Enclosing composable function is not called
                scope.launch {
                    networkCall()
                    loading = false
                    alphaValue = 1f
                }
            },
            enabled = !loading
        ) {
            Text(text = "Launch coroutine")
        }
    }
}

suspend fun networkCall() {
    delay(3000)
}
