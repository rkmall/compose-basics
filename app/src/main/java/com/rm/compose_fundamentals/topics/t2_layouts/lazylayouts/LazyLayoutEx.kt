package com.rm.compose_fundamentals.topics.t2_layouts.lazylayouts

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rm.compose_fundamentals.R
import kotlinx.coroutines.launch

@Composable
fun LazyLayoutColumn() {
    val scope = rememberCoroutineScope()

    val state = rememberLazyListState()

    val showScrollToTopButton by remember {
        derivedStateOf { state.firstVisibleItemIndex == 0 }
    }

    LazyColumn(
        state = state,
        verticalArrangement = Arrangement.spacedBy(4.dp), // space between each items
        contentPadding = PaddingValues( // padding around the whole list
            vertical = 12.dp,
            horizontal = 10.dp
        )
    ) {
        item {
            Text(
                text = "Guitar List",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        items(
            items = getItemData(),
            key = { item -> item.id }
        ) { guitar ->
            ListItem(
                drawable = guitar.drawable,
                title = guitar.name,
                description = guitar.description
            )
        }
    }

    if (showScrollToTopButton) {
        ScrollToTopButton(
            onClick = {
                scope.launch {
                    state.scrollToItem(index = 0)
                }
            }
        )
    }
}

@Composable
fun ScrollToTopButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .shadow(10.dp, shape = CircleShape)
                .clip(CircleShape)
                .size(65.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black.copy(alpha = 0.7f)
            )
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "arrow up"
            )
        }
    }
}


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
                .background(Color(0xFFB2DFDB)),
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
                modifier = Modifier.height(88.dp), // match height with the Image height to level title text
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


@Preview
@Composable
private fun PreviewLazyLayoutColumn() {
    LazyLayoutColumn()
}

@Preview
@Composable
private fun PreviewScrollToTopButton() {
    ScrollToTopButton(
        onClick = {}
    )
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
