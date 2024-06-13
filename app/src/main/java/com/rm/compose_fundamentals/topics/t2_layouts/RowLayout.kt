package com.rm.compose_fundamentals.topics.t2_layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RowLayoutBasicSetup() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RowContent(title = "Hello")
        RowContent(title = "Hallo")
        RowContent(title = "Hola")
        RowContent(title = "Bonjour")
    }
}

@Preview
@Composable
private fun PreviewRowLayoutBasicSetup() {
    RowLayoutBasicSetup()
}

@Composable
fun RowContent(
    title: String
) {
    Card(
        modifier = Modifier
            .sizeIn(
                minWidth = 25.dp,
                maxWidth = 80.dp,
                minHeight = 25.dp,
                maxHeight = 80.dp
            )
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
private fun PreviewRowContent() {
    RowContent(title = "Hello")
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

@Preview
@Composable
private fun PreviewRowArrangementStart() {
    RowArrangementStart()
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

@Preview
@Composable
private fun PreviewRowArrangementCenter() {
    RowArrangementCenter()
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

@Preview
@Composable
private fun PreviewRowArrangementEnd() {
    RowArrangementEnd()
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

@Preview
@Composable
private fun PreviewRowArrangementSpaceBetween() {
    RowArrangementSpaceBetween()
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

@Preview
@Composable
private fun PreviewRowArrangementSpaceEvenly() {
    RowArrangementSpaceEvenly()
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

@Preview
@Composable
private fun PreviewRowArrangementSpaceAround() {
    RowArrangementSpaceAround()
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

@Preview
@Composable
private fun PreviewRowAlignmentTop() {
    RowAlignmentTop()
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

@Preview
@Composable
private fun PreviewRowAlignmentCenterVertically() {
    RowAlignmentCenterVertically()
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

@Preview
@Composable
private fun PreviewRowAlignmentBottom() {
    RowAlignmentBottom()
}

@Composable
fun RowText(text: String) {
    Text(
        modifier = Modifier.background(Color.Green),
        text = text,
        fontSize = 25.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Black
    )
}
