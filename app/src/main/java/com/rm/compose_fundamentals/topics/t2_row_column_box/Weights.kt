package com.rm.compose_fundamentals.topics.t2_row_column_box

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun PreviewWeights() {
    ColumnWeight()
    //RowWeight()
}

@Composable
fun ColumnWeight() {
    Row {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .background(Color.Yellow),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "hello",
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .background(Color.Green)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "there",
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun RowWeight() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Yellow),
            horizontalArrangement = Arrangement.Center  // Positions child
        ) {
            Text(
                text = "hello",
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .background(Color.Green)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(), // Child fills max width
                text = "there",
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center        // Child positions itself
            )
        }
    }
}
