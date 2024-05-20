package com.rm.compose_fundamentals.topics.t3_effects

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Preview
@Composable
fun PreviewDisposableEffect() {
    DisposableEffectEx()
}

@Composable
fun DisposableEffectEx() {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { owner, event ->
            if (event == Lifecycle.Event.ON_PAUSE) {
                Log.d("state","On pause called")
            }
        }

        // Add Observer
        lifecycleOwner.lifecycle.addObserver(observer)

        // Remove Observer, this can't be done using LaunchedEffect
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}