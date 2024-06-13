package com.rm.compose_fundamentals.topics.t7_components.card

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MessageCard(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.large.copy(topStart = CornerSize(0.dp)),
    color: CardColors = CardDefaults.cardColors(containerColor = Color(0xFF49517F)),
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        shape = shape,
        colors = color
    ) {
        content()
    }
}

@Preview
@Composable
private fun PreviewMessageCard() {
    MessageCard(
        modifier = Modifier.fillMaxWidth(),
        content = {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "Hello there",
                color = Color.White,
                fontSize = 18.sp
            )
        }
    )
}