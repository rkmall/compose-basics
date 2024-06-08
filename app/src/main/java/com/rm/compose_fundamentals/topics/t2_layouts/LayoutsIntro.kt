package com.rm.compose_fundamentals.topics.t2_layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rm.compose_fundamentals.ui.theme.ComposefundamentalsTheme


@Preview(showSystemUi = false)
@Composable
fun ColumnRowBoxPreview() {
    ComposefundamentalsTheme {
        //ColumnLayout()
        //RowLayout()
        //BoxLayout()
    }
}

/**
 * When using Column, think of the layout space as:
 *  - 1 Column that can contain multiple Rows.
 */
@Composable
fun ColumnLayout(modifier: Modifier = Modifier) {
    Column {
        Text("Row 1", modifier.background(Color.Green))
        Text("Row 2", modifier.background(Color.Yellow))
        Text("Row 3", modifier.background(Color.Cyan))
    }
}

/**
 * When using Row, think of the layout space as:
 *  - 1 Row that can contain multiple Columns.
 */
@Composable
fun RowLayout(modifier: Modifier = Modifier) {
    Row {
        Text("Column 1", modifier.background(Color.Green))
        Text("Column 2", modifier.background(Color.Yellow))
        Text("Column 3", modifier.background(Color.Cyan))
    }
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
            contentAlignment = Alignment.TopStart
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