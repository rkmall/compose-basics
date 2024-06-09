package com.rm.compose_fundamentals.topics.t2_layouts

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

// Compose stacks Text elements on top of each other.
@Preview
@Composable
fun PreviewDefaultComposeConstraints() {
    DefaultComposeConstraints()
}

@Composable
fun DefaultComposeConstraints() {
    Text("Jim Dunlop")
    Text("Guitar Picks")
}

// Use standard layout Column to place element vertically.
@Preview
@Composable
fun PreviewUseComposeLayout() {
   UseComposeLayout()
}

@Composable
fun UseComposeLayout() {
    Column {
        Text("Jim Dunlop")
        Text("Guitar Picks")
    }
}

@Preview
@Composable
fun PreviewBoxLayout() {
    ArtistAvatar(
        artist = Artist(
            image = R.drawable.logo,
            name ="Jim Dunlop",
            lastSeen = "3 minutes ago")
    )
}

@Composable
fun ArtistAvatar(artist: Artist) {
    Box(contentAlignment = Alignment.BottomEnd) {
        Image(painterResource(id = artist.image), contentDescription = "Artist image")
        Icon(Icons.Filled.Check, contentDescription = "Check mark" )
    }
}

@Preview
@Composable
fun PreviewComposeReused() {
    ArtistCardWithImage(
        artist = Artist(
            image = R.drawable.logo,
            name ="Jim Dunlop",
            lastSeen = "3 minutes ago")
    )
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

class Artist(
    @DrawableRes val image: Int,
    val name: String,
    val lastSeen: String
) 