package com.rm.compose_fundamentals.topics.t7_components.examples

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rm.compose_fundamentals.R

@Preview
@Composable
fun FilledCardsPreview() {
    FilledCardExample()
}

@Preview
@Composable
fun ElevatedCardsPreview() {
    ElevatedCardExample()
    //OutlinedCardExample()
}

@Preview
@Composable
fun OutlinedCardsPreview() {
    OutlinedCardExample()
}

@Preview
@Composable
fun ImageCardPreview() {
    ImageCard(
        painterResource(id = R.drawable.fender),
        "guitar"
    )
}


@Composable
fun FilledCardExample() {
    Card(
        modifier = Modifier.size(200.dp, 100.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(
            text = "Filled Card",
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun ElevatedCardExample() {
    ElevatedCard(
        modifier = Modifier.size(200.dp, 100.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Elevated Card",
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun OutlinedCardExample() {
    OutlinedCard(
        modifier = Modifier.size(200.dp, 100.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = Color.Black
        ),
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = "Outlined Card",
        )
    }
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    // Box is required to match the size of the image so that
    // background beyond the image size is not displayed
    Box(
        modifier = Modifier.size(200.dp, 100.dp)
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Box {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                ),
                                startY = 150f
                            )
                        ),
                    contentAlignment = Alignment.BottomStart
                ){
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Fender is great! Let's Rock!",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

