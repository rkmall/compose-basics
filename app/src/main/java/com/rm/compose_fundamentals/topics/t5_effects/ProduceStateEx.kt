package com.rm.compose_fundamentals.topics.t5_effects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@Preview
@Composable
fun PreviewProduceStateEx() {
    produceStateEx(countUpTo = 10)
}

@Composable
fun produceStateEx(countUpTo: Int): State<Int> {
    return produceState(initialValue = 0) {
        while (this.value < countUpTo) {
            delay(1000L)
            value++
        }
    }
}