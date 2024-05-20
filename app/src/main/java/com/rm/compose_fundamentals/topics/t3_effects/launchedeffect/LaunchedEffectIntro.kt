package com.rm.compose_fundamentals.topics.t3_effects.launchedeffect

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@Preview
@Composable
fun PreviewLaunchedEffectIntro() {
    LaunchEffectIntro()
}

@Composable
fun LaunchEffectIntro() {
    Log.d("launch", "Composable called")

    var text by remember { mutableStateOf("") }
    var index by remember { mutableIntStateOf(1) }

    // key1 denotes that whenever the "text" changes, the LaunchedEffect Coroutine
    // will be cancelled and relaunched with the new text value.
    LaunchedEffect(key1 = text) {
        // This is a coroutineScope
        Log.d("launch", "LaunchedEffect called")

        delay(1000)
        Log.d("launch", text)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // This button changes the text and triggers this Composable to relaunch LaunchedEffect
        Button(onClick = { text = "hello ${++index}" }) {
            Text(text = "Click me")
        }
    }
}