package com.rm.compose_fundamentals.topics.t2_layouts.lazylayouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun LazyColumnWithScrollState() {
    val scope = rememberCoroutineScope()
    val state = rememberLazyListState()

    val showScrollToTopButton by remember {
        derivedStateOf { state.firstVisibleItemIndex > 0 }
    }

    LazyColumn(
        modifier = Modifier.background(Color.Black),
        state = state,
        verticalArrangement = Arrangement.spacedBy(4.dp), // space between each items
        contentPadding = PaddingValues( // padding around the whole list
            vertical = 12.dp,
            horizontal = 10.dp
        )
    ) {
        item {
            ListCategoryCard(title = "Guitar List 1")
        }

        items(
            items = getListOne(),
            key = { it.id } // use unique key to avoid unnecessary compositions
        ) { item ->
            ListItem(
                drawable = item.drawable,
                title = item.name,
                description = item.description
            )
        }

        item {
            ListCategoryCard(title = "Guitar List 2")
        }

        items(
            items = getListTwo(),
            key = { it.id }   // if there are multiple lists each item key must be unique
        ) { item ->
            ListItem(
                drawable = item.drawable,
                title = item.name,
                description = item.description
            )
        }
    }

    if (showScrollToTopButton) {
        ScrollToTopButton(
            onClick = {
                scope.launch {
                    state.scrollToItem(index = 0) // scroll to the top element in the list
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

@Preview
@Composable
private fun PreviewLazyLayoutColumn() {
    LazyColumnWithScrollState()
}

@Preview
@Composable
private fun PreviewScrollToTopButton() {
    ScrollToTopButton(
        onClick = {}
    )
}
