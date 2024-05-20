package com.rm.compose_fundamentals.topics.t2_row_column_box

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
fun PreviewRowsPositions() {
    //RowArrangementStart()
    //RowArrangementCenter()
    //RowArrangementEnd()
    //RowArrangementSpaceBetween()
    //RowArrangementSpaceEvenly()
    //RowArrangementSpaceAround()

    //RowAlignmentTop()
    //RowAlignmentCenterVertically()
    //RowAlignmentBottom()
}

@Composable
fun RowArrangementStart() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Start
    ) {
        RowText(text = "Hello")
        RowText(text = "World")
        RowText(text = "Compose")
    }
}

@Composable
fun RowArrangementCenter() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center
    ) {
        RowText(text = "Hello")
        RowText(text = "World")
        RowText(text = "Compose")
    }
}

@Composable
fun RowArrangementEnd() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.End
    ) {
        RowText(text = "Hello")
        RowText(text = "World")
        RowText(text = "Compose")
    }
}

@Composable
fun RowArrangementSpaceBetween() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RowText(text = "Hello")
        RowText(text = "World")
        RowText(text = "Compose")
    }
}

@Composable
fun RowArrangementSpaceEvenly() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        RowText(text = "Hello")
        RowText(text = "World")
        RowText(text = "Compose")
    }
}

@Composable
fun RowArrangementSpaceAround() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        RowText(text = "Hello")
        RowText(text = "World")
        RowText(text = "Compose")
    }
}

@Composable
fun RowAlignmentTop() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Top,
    ) {
        RowText(text = "Hello")
        RowText(text = "World")
        RowText(text = "Compose")
    }
}

@Composable
fun RowAlignmentCenterVertically() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RowText(text = "Hello")
        RowText(text = "World")
        RowText(text = "Compose")
    }
}

@Composable
fun RowAlignmentBottom() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Bottom,
    ) {
        RowText(text = "Hello")
        RowText(text = "World")
        RowText(text = "Compose")
    }
}

@Composable
fun RowText(text: String) {
    Text(
        modifier = Modifier.background(Color.Yellow),
        text = text,
        fontSize = 30.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Black
    )
}
