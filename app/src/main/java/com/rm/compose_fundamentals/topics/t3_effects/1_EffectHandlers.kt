package com.rm.compose_fundamentals.topics.t3_effects

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PreviewComposableWithSideEffect() {
    ComposableWithSideEffect()
}

private var i = 0
@Composable
fun ComposableWithSideEffect() {
    var text by remember { mutableStateOf("") }

    Button(onClick = { text += "#"}) {
        i++  // side-effect that escapes the scope of Composable
        Text(text = "$text $i")
    }
}