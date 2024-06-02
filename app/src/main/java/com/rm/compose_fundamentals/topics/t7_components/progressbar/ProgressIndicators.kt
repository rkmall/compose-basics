package com.rm.compose_fundamentals.topics.t7_components.progressbar

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
fun PreviewLinearProgressIndicator() {
    LinearOrCircularProgressIndicatorExample()
}

@Preview
@Composable
fun PreviewIndeterminateCircularIndicator() {
    IndeterminateCircularIndicatorExample()
}

@Preview
@Composable
fun PreviewCustomCircularIndicatorUse() {
    CustomCircularIndicatorUse()
}

@Composable
fun LinearOrCircularProgressIndicatorExample() {
    var currentProgress by remember { mutableFloatStateOf(0f) }
    var loading by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {
                loading = true

                // This coroutine will be called once where the progress is updated
                // by changing the currentProgress (mutableState)
                // This triggers this composable each time with the increase in progress
                // i.e. this Composable will be triggered 100 times
                scope.launch {
                    loadProgress { progress ->
                        Log.d("progress", "$progress")
                        currentProgress = progress
                    }
                    loading = false // reset loading when coroutine done
                }
            },
            enabled = !loading
        ) {
            Text(text = "Load")
        }

        LinearProgressIndicator(
            progress = currentProgress,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun IndeterminateCircularIndicatorExample() {
    var loading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
            .size(150.dp),
            contentAlignment = Alignment.Center
        ) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { loading = true },
                enabled = !loading
            ) {
                Text("Load")
            }
            Button(
                onClick = { loading = false },
                enabled = loading
            ) {
                Text("Stop")
            }
        }
    }
}

@Composable
fun CustomCircularIndicatorUse() {
    var completionText by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var currentPercentage by remember { mutableFloatStateOf(0f) }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(150.dp),
            contentAlignment = Alignment.Center
        ) {
            if(loading) (
                    CustomCircularIndicator(
                        percentage = currentPercentage,
                        number = 100
                    )
            ) else {
                Text(
                    text = completionText,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    loading = true

                    scope.launch {
                        loadProgress { progress ->
                            currentPercentage = progress
                        }
                        loading = false
                        completionText = "Done"
                    }
                },
                enabled = !loading
            ) {
                Text(text = "Load")
            }
        }
    }
}

@Composable
fun CustomCircularIndicator(
    percentage: Float,      // progress percentage
    number: Int,            // max number when full circumference is covered
    radius: Dp = 50.dp,
    fontSize: TextUnit = 28.sp,
    color: Color = Color.Green,
    strokeWidth: Dp = 8.dp,
) {
    Box(
        modifier = Modifier.size(radius * 2.5f),
        contentAlignment = Alignment.Center
    ) {
        // This canvas is called each time when the percentage is updated
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * percentage,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        Text(
            text = (percentage * number).toInt().toString(),
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    for (i in 1..100) {
        updateProgress(i.toFloat()/100)
        delay(50)
    }
}

@Preview
@Composable
fun PreviewCircularIndicatorInsideButton() {
    CircularIndicatorInsideButton(
        loading = true
    ) {}
}


@Composable
fun CircularIndicatorInsideButton(
    loading: Boolean = false,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
                .padding(12.dp)
                .clickable(enabled = !loading) { onClick() }
                .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Icon" )

            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Click Me",
                fontSize = 28.sp
            )

            if (loading) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(24.dp),
                    strokeWidth = 2.dp,
                    color = Color.Green
                )
            }
        }
    }

}


