package com.rm.compose_fundamentals.topics.t2_layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BoxInColumn() {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(30) {
                Text(
                    text = "Item: $it",
                    fontSize = 24.sp,
                    color = Color.Blue,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        MBox()
}


@Composable
fun MBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "arrow up"
            )
        }
    }
}

@Preview
@Composable
private fun PreviewBoxInColumn() {
    BoxInColumn()
}

@Preview
@Composable
private fun PreviewMBox() {
    MBox()
}

@Preview
@Composable
fun PreviewBoxPositions() {
    BoxAlignContentTopStart()
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
