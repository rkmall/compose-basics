package com.rm.compose_fundamentals.topics.t2_layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun PreviewColumnsPositions() {
    //ColumnArrangementTop()
    //ColumnArrangementCenter()
    //ColumnArrangementBottom()
    //ColumnArrangementSpaceBetween()
    //ColumnArrangementSpaceEvenly()
    //ColumnArrangementSpaceAround()

    //ColumnAlignmentStart()
    //ColumnAlignmentCenterHorizontally()
    //ColumnAlignmentEnd()
}

@Composable
fun ColumnArrangementTop() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        TextContent(text = "Hello")
        TextContent(text = "World")
        TextContent(text = "Compose")
    }
}

@Composable
fun ColumnArrangementCenter() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        TextContent(text = "Hello")
        TextContent(text = "World")
        TextContent(text = "Compose")
    }
}

@Composable
fun ColumnArrangementBottom() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        TextContent(text = "Hello")
        TextContent(text = "World")
        TextContent(text = "Compose")
    }
}

@Composable
fun ColumnArrangementSpaceBetween() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TextContent(text = "Hello")
        TextContent(text = "World")
        TextContent(text = "Compose")
    }
}

@Composable
fun ColumnArrangementSpaceEvenly() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        TextContent(text = "Hello")
        TextContent(text = "World")
        TextContent(text = "Compose")
    }
}

@Composable
fun ColumnArrangementSpaceAround() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        TextContent(text = "Hello")
        TextContent(text = "World")
        TextContent(text = "Compose")
    }
}

@Composable
fun ColumnAlignmentStart() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        TextContent(text = "Hello")
        TextContent(text = "World")
        TextContent(text = "Compose")
    }
}

@Composable
fun ColumnAlignmentCenterHorizontally() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextContent(text = "Hello")
        TextContent(text = "World")
        TextContent(text = "Compose")
    }
}

@Composable
fun ColumnAlignmentEnd() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End
    ) {
        TextContent(text = "Hello")
        TextContent(text = "World")
        TextContent(text = "Compose")
    }
}

@Composable
fun TextContent(text: String) {
    Text(
        modifier = Modifier.background(Color.Yellow),
        text = text,
        fontSize = 30.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Black
    )
}
