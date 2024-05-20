package com.rm.compose_fundamentals.topics.t3_effects

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@Preview
@Composable
fun PreviewRememberUpdatedState() {
    RememberUpdatedState{
        Log.d("state", "onTimeOut called")
    }
}

@Composable
fun RememberUpdatedState(
    onTimeout: () -> Unit
) {
    val updatedOnTimeout by rememberUpdatedState(newValue = onTimeout)

    LaunchedEffect(key1 = Unit) {
        delay(3000)
        updatedOnTimeout() // if onTimeout changes this updatedOnTimeout will be applied
    }                      // even though the LaunchedEffect is only called once
}
