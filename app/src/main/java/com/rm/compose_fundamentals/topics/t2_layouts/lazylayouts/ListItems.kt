package com.rm.compose_fundamentals.topics.t2_layouts.lazylayouts

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rm.compose_fundamentals.R

@Composable
fun ListItem(
    @DrawableRes drawable: Int,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(8),
        modifier = modifier.padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFFB2DFDB))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(88.dp)
                    .clip(CircleShape)
            )

            Column(
                modifier = Modifier.height(88.dp), // match height with the Image height
                verticalArrangement = Arrangement.spacedBy(8.dp) // space between title and description
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Text(
                    text = description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
fun ListCategoryCard(
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        border = BorderStroke(1.dp, Color.Black)

    ) {
        Text(
            text = title,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewListCategoryCard() {
    ListCategoryCard("Guitars")
}


@Preview
@Composable
private fun PreviewListItem() {
    ListItem(
        drawable = R.drawable.ibanez,
        title = "Ibanez",
        description = "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
    )
}


fun getListOne(): List<Guitar> {
    return listOf(
        Guitar(
            1,
            R.drawable.fender,
            "Fender",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            2,
            R.drawable.gibson,
            "Gibson",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            3,
            R.drawable.ibanez,
            "Ibanez",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            4,
            R.drawable.prs,
            "PRS",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            5,
            R.drawable.jackson,
            "Jackson",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            6,
            R.drawable.esp,
            "ESP",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            7,
            R.drawable.musicman,
            "Musicman",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            8,
            R.drawable.charvel,
            "Charvel",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        )
    )
}

fun getListTwo(): List<Guitar> {
    return listOf(
        Guitar(
            9,
            R.drawable.fender,
            "Fender",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            10,
            R.drawable.gibson,
            "Gibson",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            11,
            R.drawable.ibanez,
            "Ibanez",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            12,
            R.drawable.prs,
            "PRS",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            13,
            R.drawable.jackson,
            "Jackson",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            14,
            R.drawable.esp,
            "ESP",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            15,
            R.drawable.musicman,
            "Musicman",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            16,
            R.drawable.charvel,
            "Charvel",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        )
    )
}

data class Guitar(
    val id: Int,
    @DrawableRes val drawable: Int,
    val name: String,
    val description: String
)