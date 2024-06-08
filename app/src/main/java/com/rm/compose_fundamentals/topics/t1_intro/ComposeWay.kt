package com.rm.compose_fundamentals.topics.t1_intro

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(
    name = "Small Font",
    group = "Font scales",
    fontScale = 0.5f
)
@Preview(
    name = "Large Font",
    group = "Font scales",
    fontScale = 1.5f
)
annotation class FontScalePreviews

@Preview(
    name = "Dark mode",
    group = "UI mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
@Preview(
    name = "Light mode",
    group = "UI mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
annotation class DarkLightPreviews

@FontScalePreviews
@DarkLightPreviews
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