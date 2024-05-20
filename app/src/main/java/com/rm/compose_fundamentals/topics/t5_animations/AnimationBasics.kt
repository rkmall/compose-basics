package com.rm.compose_fundamentals.topics.t5_animations

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun PreviewAnimateBoxSizeWithTween() {
    AnimateBoxSizeWithTween()
}

@Preview
@Composable
fun PreviewAnimateBoxSizeAndColorWithTween() {
    AnimateBoxSizeAndColorWithTween()
}

@Preview
@Composable
fun PreviewAnimateBoxSizeWithSpring() {
    AnimateBoxSizeWithSpring()
}

@Preview
@Composable
fun PreviewAnimateBoxSizeWithKeyframes() {
    AnimateBoxSizeWithKeyframes()
}


@Composable
fun AnimateBoxSizeWithTween() {
    // Initial box size
    var sizeState by remember { mutableStateOf(200.dp) }

    // Animate box size state
    val size by animateDpAsState(
        targetValue = sizeState, // when target value changes, animation auto-runs
        label = "dpAsState",
        animationSpec = tween(      // animation that changes the target value through time
            durationMillis = 2000,  // total time taken by animation
            delayMillis = 50,       // time before animation starts
            easing = LinearOutSlowInEasing  // animation curve used to interpolate between start and end
        )
    )

    Box(
        modifier = Modifier
            .size(size)             // use animated size
            .background(Color.Cyan),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { sizeState += 50.dp } // increase the size
        ) {
            Text(text = "Animate Box Size")
        }
    }
}

@Composable
fun AnimateBoxSizeAndColorWithTween() {
    var sizeState by remember { mutableStateOf(200.dp) }

    val size by animateDpAsState(
        targetValue = sizeState,
        label = "dpAsState",
        animationSpec = tween(durationMillis = 1000)
    )

    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val color by infiniteTransition.animateColor(
        initialValue = Color.Cyan,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "infiniteTransitionColor"
    )

    Box(
        modifier = Modifier
            .size(size)             // use animated size
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { sizeState += 50.dp } // increase the size
        ) {
            Text(text = "Animate Box Size")
        }
    }
}


@Composable
fun AnimateBoxSizeWithSpring() {
    var sizeState by remember { mutableStateOf(200.dp) }

    val size by animateDpAsState(
        targetValue = sizeState,
        label = "DpAnimation",
        animationSpec = spring(
            Spring.DampingRatioHighBouncy
        )
    )

    Box(
        modifier = Modifier
            .size(size)
            .background(Color.Cyan)
    ) {
        Button(
            modifier = Modifier
                .align(Alignment.Center),

            onClick = {
                sizeState += 50.dp
            }
        ) {
            Text(text = "Animate Box Size")
        }
    }
}

@Composable
fun AnimateBoxSizeWithKeyframes() {
    var sizeState by remember { mutableStateOf(200.dp) }

    val size by animateDpAsState(
        targetValue = sizeState,
        label = "DpAnimation",
        animationSpec = keyframes {
            durationMillis = 2000
            sizeState at 0 using LinearEasing
            sizeState * 2f at 1000 using FastOutSlowInEasing
            sizeState *3f at 2000
        }
    )

    Box(
        modifier = Modifier
            .size(size)
            .background(Color.Cyan)
    ) {
        Button(
            modifier = Modifier
                .align(Alignment.Center),

            onClick = {
                sizeState += 50.dp
            }
        ) {
            Text(text = "Animate Box Size")
        }
    }
}
