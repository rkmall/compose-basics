package com.rm.compose_fundamentals.topics.t2_layouts

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rm.compose_fundamentals.R

// Compose stacks elements on top of each other.
@Composable
fun DefaultComposeConstraints() {
    Text("Jim Dunlop")
    Text("Guitar Picks")
}

@Preview
@Composable
fun PreviewDefaultComposeConstraints() {
    DefaultComposeConstraints()
}

// Use standard layout such as Column to place elements vertically.
@Composable
fun UseComposeLayout() {
    Column {
        Text("Jim Dunlop")
        Text("Guitar Picks")
    }
}

@Preview
@Composable
fun PreviewUseComposeLayout() {
    UseComposeLayout()
}

/**
 * Reuse the Composable
 */
@Composable
fun ArtistCard(artist: Artist) {
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

@Preview
@Composable
fun PreviewArtistCard() {
    ArtistCard(
        artist = Artist(
            image = R.drawable.logo,
            name ="Jim Dunlop",
            lastSeen = "3 minutes ago")
    )
}

/**
 * Start with the Composable on the lowest level in the hierarchy (node).
 */
@Composable
fun ArtistAvatar(artist: Artist) {
    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        Image(
            modifier = Modifier
                .size(80.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = artist.image),
            contentDescription = "Artist image"
        )

        Icon(Icons.Filled.Check, contentDescription = "Check mark" )
    }
}

@Preview
@Composable
fun PreviewArtistAvatar() {
    ArtistAvatar(
        artist = Artist(
            image = R.drawable.logo,
            name ="Jim Dunlop",
            lastSeen = "3 minutes ago")
    )
}

class Artist(
    @DrawableRes val image: Int,
    val name: String,
    val lastSeen: String
) 