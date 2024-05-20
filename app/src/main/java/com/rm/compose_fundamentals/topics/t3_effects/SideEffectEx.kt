package com.rm.compose_fundamentals.topics.t3_effects

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PreviewSideEffectEx() {
    SideEffectEx(nonComposeCounter = 10)
}

@Composable
fun SideEffectEx(nonComposeCounter: Int) {
    
    var text by remember { mutableStateOf("") }
    
    SideEffect {
        println("Called after every successful recomposition")
        text = nonComposeCounter.toString()
    }
    
    Text(text = text)
}