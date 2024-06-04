package com.rm.compose_fundamentals.topics.t7_components.customcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.random.Random


@Preview
@Composable
fun PreviewCustomSpacer() {
    CustomSpacer(height = 5.dp)
}

@Composable
fun CustomSpacer(height: Dp, color: Color = getRandomColor()) {
    Box(
        modifier = Modifier
            .padding(1.dp)
            .height(height)
            .fillMaxWidth()
            .background(color)
    )
}

@Composable
fun getRandomColor(): Color {
    val random = Random.Default
    return Color(random.nextFloat(), random.nextFloat(), random.nextFloat())
}
