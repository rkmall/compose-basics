package com.rm.compose_fundamentals.topics.t5_effects.launchedeffect

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Preview
@Composable
fun PreviewLaunchedEffectWithKey() {
    LaunchedEffectWithKey(state = MyState(false))
}

@Composable
fun LaunchedEffectWithKey(state: MyState) {
    Log.d("launch", "Composition entered...")
    var result by remember { mutableStateOf("Loading...") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /**
         * LaunchedEffect is invoked when this Composable enter the composition.
         * Then, LaunchedEffect launches coroutine block in the Composition CoroutineContext.
         * This coroutine remains active through out the Composition and
         * will be cancelled when the LaunchedEffect leaves the Composition.
         * 
         * If the key changes, the current coroutine will be cancelled and
         * LaunchedEffect will be re-launched.
         */
        LaunchedEffect(key1 = state.opened) {
            // 1
            // 6 fetchData changes state
            // 10 Second LaunchedEffect changes state
            // 14 fetchData changes state
            Log.d(TAG, "LaunchedEffect with key called")

            logState(state) // 2    // 7    // 11   // 15

            if (state.opened) {
                fetchData(state) // 3 triggers state change      // 12 triggers state change
                result = "Data loaded"
            }
            
            logState(state) // 5    // 8    // 13   // 16
        }

        Text(
            text = result,
            fontSize = 26.sp
        )
    }

    LaunchedEffect(key1 = Unit) {  // 4
        Log.d(TAG, "LaunchedEffect second called")
        delay(8000)
        Log.d(TAG, "Opening state")
        state.open() // 9 triggers LaunchedEffect1
    }
}

fun logState(state: MyState) {
    if (state.opened) Log.d(TAG,"State is opened") else
        Log.d(TAG,"State is closed")
}

suspend fun fetchData(state: MyState) {
    Log.d(TAG,"Fetching data")
    delay(2000)
    state.close()
}

class MyState(clicked: Boolean = false) {
    var opened by mutableStateOf(clicked)
        private set

    fun open() { opened = true }

    fun close() { opened = false }
}

private const val TAG = "launch"
