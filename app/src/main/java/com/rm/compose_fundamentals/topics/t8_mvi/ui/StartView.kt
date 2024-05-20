package com.rm.compose_fundamentals.topics.t8_mvi.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rm.compose_fundamentals.R
import com.rm.compose_fundamentals.topics.t8_mvi.favorite.FavoriteViewModel

@Composable
fun StartView(
    viewModel: FavoriteViewModel,
    onFavorite: () -> Unit
) {
    Surface(
        color = Color.White
    ) {
        Column(
            Modifier
                .fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.cupcake),
                contentDescription = "Cupcake",
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.padding(vertical = 20.dp))

            IconButton(onClick = { onFavorite() }) {
                Icon(
                    modifier = Modifier
                        .size(40.dp),
                    imageVector = if (viewModel.state.favorite) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = "Favorites",
                    tint = if (viewModel.state.favorite) {
                        Color.Red
                    } else {
                        Color.Gray
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    StartView(viewModel = FavoriteViewModel()) {
        
    }
}