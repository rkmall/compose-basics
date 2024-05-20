package com.rm.compose_fundamentals.topics.t2_row_column_box

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun PreviewBoxPositions() {
    //BoxAlignContentTopStart()
    //BoxAlignContentTopCenter()
    //BoxAlignContentTopEnd()
    //BoxAlignContentCenterStart()
    //BoxAlignContentCenter()
    //BoxAlignContentCenterEnd()
    //BoxAlignContentBottomStart()
    //BoxAlignContentBottomCenter()
    //BoxAlignContentBottomEnd()
}


@Composable
fun BoxAlignContentTopStart() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.TopStart
    ) {
        Text(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Yellow),
            text = "Hello",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BoxAlignContentTopCenter() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Yellow),
            text = "Hello",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BoxAlignContentTopEnd() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.TopEnd
    ) {
        Text(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Yellow),
            text = "Hello",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BoxAlignContentCenterStart() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Yellow),
            text = "Hello",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BoxAlignContentCenter() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Yellow),
            text = "Hello",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BoxAlignContentCenterEnd() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Yellow),
            text = "Hello",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BoxAlignContentBottomStart() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.BottomStart
    ) {
        Text(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Yellow),
            text = "Hello",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BoxAlignContentBottomCenter() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Yellow),
            text = "Hello",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BoxAlignContentBottomEnd() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.BottomEnd
    ) {
        Text(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Yellow),
            text = "Hello",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}
