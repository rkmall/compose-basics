package com.rm.compose_fundamentals.topics.t2_layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rm.compose_fundamentals.ui.theme.ComposefundamentalsTheme

/**
 * When using Column, think of the layout space as:
 *  - 1 Column that can contain multiple Rows.
 */
@Composable
fun ColumnLayout(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .background(Color.DarkGray)
            .padding(8.dp), // padding around the whole layout
        verticalArrangement = Arrangement.spacedBy(12.dp) // space between each items
    ) {
        Text(
            text = "Row 1",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.background(Color.Green)
        )
        Text(
            text = "Row 2",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.background(Color.Yellow)
        )
        Text(
            text = "Row 3",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.background(Color.Cyan)
        )
    }
}

@Preview
@Composable
private fun PreviewColumnLayout() {
    ColumnLayout()
}

/**
 * When using Row, think of the layout space as:
 *  - 1 Row that can contain multiple Columns.
 */
@Composable
fun RowLayout(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .background(Color.DarkGray)
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Column 1",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.background(Color.Green)
        )
        Text(
            text = "Column 2",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.background(Color.Yellow)
        )
        Text(
            text = "Column 3",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.background(Color.Cyan)
        )
    }
}

@Preview
@Composable
private fun PreviewRowLayout() {
    RowLayout()
}

/**
 * When using Box, think of the layout space where
 * each layout can be stacked over each other, each
 * in a specific position.
 */
@Composable
fun BoxLayout(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize(1f)
            .background(Color.Green),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = modifier
                .height(300.dp)
                .width(300.dp)
                .background(Color.Yellow),
            contentAlignment = Alignment.TopEnd
        ) {
            Text(
                modifier = modifier
                    .background(Color.Cyan)
                    .size(100.dp, 100.dp),
                text = "Box",
                fontSize = 30.sp,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewBoxLayout() {
    BoxLayout()
}
