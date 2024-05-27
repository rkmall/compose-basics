package com.rm.compose_fundamentals.topics.t5_effects

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PreviewDerivedStateOfEx() {
    DerivedStateOfEx()
}

@Composable
fun DerivedStateOfEx() {
    var counter by remember { mutableStateOf(0) }

    val counterText by remember {
        // Suppose this is an expensive computation,
        // deriveStateOf caches the result of the computation such that calling State.value
        // will not cause the calculations be executed multiple times.
        derivedStateOf {
            Log.d("state", "DerivedStateOf called")
            "The counter is $counter"
        }
    }

    Button(onClick = { counter++ }) {
        Text(text = counterText)
    }
}