package com.rm.compose_fundamentals.topics.t5_effects.launchedeffect

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Preview
@Composable
fun PreviewLaunchedEffectIntro() {
    LaunchedEffectIntro()
}

@Composable
fun LaunchedEffectIntro() {
    Log.d("launch", "Composition entered...")

    /**
     * LaunchedEffect is invoked when this Composable enter the composition.
     * Then, LaunchedEffect launches coroutine block in the Composition CoroutineContext.
     * This coroutine remains active through out the Composition and
     * will be cancelled when the LaunchedEffect leaves the Composition.
     *
     * Since, the key type is Unit, the LaunchedEffect will be only launched once
     * (never re-launched).
     */
    LaunchedEffect(key1 = Unit) {
        // This is a coroutineScope
        Log.d("launch", "LaunchedEffect called")
        delay(3000)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {}) {
            Text(text = "Click me")
        }
    }
}

@Preview
@Composable
fun PreviewLaunchedEffectKeyChange() {
    LaunchEffectKeyChange()
}

@Composable
fun LaunchEffectKeyChange() {
    Log.d("launch", "Composition entered...")

    var text by remember { mutableStateOf("") }
    var index by remember { mutableIntStateOf(1) }

    // key1 denotes that whenever the "text" changes, the LaunchedEffect Coroutine
    // will be cancelled and relaunched with the new text value.
    LaunchedEffect(key1 = text) {

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