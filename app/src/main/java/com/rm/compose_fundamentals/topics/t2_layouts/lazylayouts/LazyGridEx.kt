package com.rm.compose_fundamentals.topics.t2_layouts.lazylayouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun LazyVerticalGridWithState() {
    val scope = rememberCoroutineScope()
    val state = rememberLazyGridState()

    val showScrollToTop by remember {
        derivedStateOf { state.firstVisibleItemIndex > 0 }
    }

    LazyVerticalGrid(
        modifier = Modifier
            .background(Color.Black),
        state = state,
        columns = GridCells.Fixed(2), // vertical grid with fixed no. of columns
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp)
    ) {

        item(
            // maxLineSpan == no. of columns, meaning item will cover the width of 2 grid cells
            span = { GridItemSpan(maxLineSpan) }
        ) {
            ListCategoryCard(title = "Guitar List 1")
        }
        items(
            items = getListOne(),
            key = { item -> item.id }
        ) { item ->
            ListItem(
                drawable = item.drawable,
                title = item.name,
                description = item.description
            )
        }

        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            ListCategoryCard(title = "Guitar List 2")
        }
        items(
            items = getListTwo(),
            key = { item -> item.id }
        ) { item ->
            ListItem(
                drawable = item.drawable,
                title = item.name,
                description = item.description
            )
        }
    }

    if (showScrollToTop) {
        ScrollToTopButton(
            onClick = {
                scope.launch {
                    state.scrollToItem(0)
                }
            }
        )
    }
}

@Composable
fun LazyVerticalGridCustom() {
    LazyVerticalGrid(
        columns = object : GridCells {
            // This method calculates the column configurations and returns
            // list of columns with calculated widths.
            override fun Density.calculateCrossAxisCellSizes(
                availableSize: Int, // available width
                spacing: Int        // requested horizontal spacing between items
            ): List<Int> {
                // Set first column twice the width of the second column
                val firstColumn = (availableSize - spacing) * 2/3
                val secondColumn = availableSize - spacing - firstColumn
                return listOf(firstColumn, secondColumn)
            }
        }
    ) {
        items(getListOne()) { item ->
            ListItem(
                drawable = item.drawable,
                title = item.name,
                description = item.description
            )
        }
    }
}

@Composable
fun LazyVerticalGridAdaptive() {
    LazyVerticalGrid(
        // Specify width for each item and the Grid will try to fit as many as items
        columns = GridCells.Adaptive(80.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp), // space between each item in horizontal arrangement
        verticalArrangement = Arrangement.spacedBy(8.dp),   // space between each item in vertical arrangement
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp) // padding around the whole list
    ) {
        items(getListOne()) { item ->
            ListItem(
                drawable = item.drawable  ,
                title = item.name,
                description = item.description
            )
        }
    }
}

@Composable
fun LazyVerticalGridFixed() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),                  // specify number of columns
        horizontalArrangement = Arrangement.spacedBy(8.dp),  // space between each item in horizontal arrangement
        verticalArrangement = Arrangement.spacedBy(8.dp),    // space between each item in vertical arrangement
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp) // padding around the whole list
    ) {
        items(getListOne()) { item ->
            ListItem(
                drawable = item.drawable,
                title = item.name,
                description = item.description
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLazyVerticalGridWithState() {
    LazyVerticalGridWithState()
}

@Preview
@Composable
private fun PreviewLazyVerticalGridCustom() {
    LazyVerticalGridCustom()
}

@Preview
@Composable
private fun PreviewLazyVerticalGridAdaptive() {
    LazyVerticalGridAdaptive()
}

@Preview
@Composable
private fun PreviewLazyVerticalGridFixed() {
    LazyVerticalGridFixed()
}