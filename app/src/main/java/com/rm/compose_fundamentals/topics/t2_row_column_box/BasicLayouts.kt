package com.rm.compose_fundamentals.topics.t2_row_column_box

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rm.compose_fundamentals.R
import com.rm.compose_fundamentals.ui.theme.ComposefundamentalsTheme

@Preview
@Composable
fun BasicLayoutsPreview() {
    ComposefundamentalsTheme {
        //ArtistCardStacked()

        //ArtistCard()

        /*ArtistAvatar(artist = Artist(
            R.drawable.img,
            "Jim Dunlop",
            "3 minutes ago"
            )
        )*/

        ArtistCardWithImage(artist = Artist(
           R.drawable.img,
           "Jim Dunlop",
           "3 minutes ago"
        ))

    }
}

// Compose stacks Text elements on top of each other.
@Composable
fun ArtistCardStacked() {
    Text("Jim Dunlop")
    Text("Guitar Picks")
}

// Use standard layout Column to place element vertically.
@Composable
fun ArtistCard() {
    Column {
        Text("Jim Dunlop")
        Text("Guitar Picks")
    }
}

@Composable
fun ArtistCardWithImage(artist: Artist) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        ArtistAvatar(artist = artist) // composable reused here
        Column(
            modifier =  Modifier
                .width(150.dp)
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = artist.name,
                fontWeight = FontWeight.Bold)
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = artist.lastSeen
            )
        }
    }
}

// Reusable Composable
@Composable
fun ArtistAvatar(artist: Artist) {
    Box(contentAlignment = Alignment.BottomEnd) {
        Image(painterResource(id = artist.image), contentDescription = "Artist image")
        Icon(Icons.Filled.Check, contentDescription = "Check mark" )
    }
}

class Artist(
    @DrawableRes val image: Int,
    val name: String,
    val lastSeen: String
) 