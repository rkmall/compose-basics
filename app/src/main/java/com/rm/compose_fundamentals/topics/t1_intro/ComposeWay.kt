package com.rm.compose_fundamentals.topics.t1_intro

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Preview
@Composable
fun PreviewHelloWorld() {
    HelloWorld()
}

@Composable
fun HelloWorld() {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = "Hello world",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Blue
    )
}

@Preview
@Composable
fun PreviewComposeIsDynamic() {
    ComposeIsDynamic(names = listOf("Cat", "Dog", "Mouse"))
}

@Composable
fun ComposeIsDynamic(names: List<String>) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Compose can be dynamic as Kotlin code, here the for loop controls
        // the Composition.
        for (name in names) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black)
                    .padding(8.dp),
                text = "Hello $name",
                fontSize = 30.sp
            )
        }
    }
}

// The functions can execute in any order and also in parallel.
@Preview
@Composable
fun PreviewCompositionOrder() {
    CompositionOrder.ComposeFunctionsDoNotExecuteInOrder()
}

private object CompositionOrder {
    @Composable
    fun ComposeFunctionsDoNotExecuteInOrder() {
        StartScreen()
        MiddleScreen()
        EndScreen()
    }
    @Composable fun StartScreen() { Log.d("order", "StartScreen") }
    @Composable fun MiddleScreen() { Log.d("order", "MiddleScreen") }
    @Composable fun EndScreen() { Log.d("order", "EndScreen") }
}