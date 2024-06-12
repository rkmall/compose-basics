package com.rm.compose_fundamentals.topics.t2_layouts.lazylayouts

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * This will throw IllegalStateException:
 *  - Vertically scrollable component was measured with an infinity
 *    maximum height constraints, which is disallowed.
 *  - One of the common reasons is nesting layouts like LazyColumn
 *    and Column(Modifier.verticalScroll()). If you want to add a header
 *    before the list of items please add a header as a separate item()
 *    before the main items() inside the LazyColumn scope.
 *
 *  - There are could be other reasons for this to happen: your ComposeView
 *    was added into a LinearLayout with some weight,
 *    you applied Modifier.wrapContentSize(unbounded = true) or
 *    wrote a custom layout. Please try to remove the source of
 *    infinite constraints in the hierarchy above the scrolling container.
 */
@Composable
fun NestedComponentsScrollableInTheSameDirectionThrowsIllegalStateException() {
    // Scrollable column
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Text(text = "HEADER")
        // LazyColumn inside Scrollable column will throw IllegalStateException
        // because both Scrollable in same direction.
        LazyColumn {
            items(30) {
                Text(text = "Item: $it", fontSize = 24.sp)
            }
        }

        Text(text = "FOOTER")
    }
}

@Composable
fun SolutionForAbove() {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        item {
            Text(
                text = "HEADER",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        items(30) {
            Text(
                text = "Item: $it",
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Green)
            )
        }

        item {
            Text(
                text = "FOOTER",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun NestedComponentScrollableInDifferentDirectionDoesNotThrowException() {
    // Scrollable Row
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "START",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxSize()
        )

        // LazyColumn inside Scrollable Row won't throw IllegalStateException
        // because both Scrollable in different directions
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(30) {
                Text(
                    text = "Item: $it",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Green)
                )
            }
        }

        Text(
            text = "END",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun MultipleItemsInSingleItem() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        /**
         * Two items are emitted in single item block. There are couple problems with this:
         *  - They are handled as one entity meaning they can't be composed individually
         *    anymore. If one element becomes visible on the screen, then all elements
         *    corresponding to the item have to be composed and measured. This causes
         *    performance issue if used excessively. It defeats the purpose of lazy layout.
         *
         *  - It also interferes scrollToItem and animateScrollToItem.
         */
        item {
            Item(id = "1")
            Item(id = "2")
        }

        item {
            Item(id = "3") // calling scrollToItem(2) will call this element
        }

        // VALID USE CASE
        item {
            Item(id = "4")
            Divider(Modifier.height(2.dp))  // not Element
        }

        item {
            Item(id = "5")
        }
    }
}

@Composable
fun CustomArrangement() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = TopWithFooter
    ) {

        item {
            Item(
                modifier = Modifier.fillMaxWidth(),
                "Header",
                TextAlign.Center
            )
        }

        item {
            Item(
                modifier = Modifier.fillMaxWidth(),
                "Body 1",
                TextAlign.Center
            )
        }

        item {
            Item(
                modifier = Modifier.fillMaxWidth(),
                "Body 2",
                TextAlign.Center
            )
        }

        item {
            Item(
                modifier = Modifier.fillMaxWidth(),
                "Footer",
                TextAlign.Center
            )
        }
    }
}

object TopWithFooter : Arrangement.Vertical {
    override fun Density.arrange(
        totalSize: Int,         // viewport height
        sizes: IntArray,        // height of the items
        outPositions: IntArray
    ) {
        var y = 0

        // Resulting item offsets is calculated in outPositions array
        // Position items one after another
        sizes.forEachIndexed { index, size ->
            outPositions[index] = y
            y += size
        }

        if (y < totalSize) {
            val lastIndex = outPositions.lastIndex
            outPositions[lastIndex] = totalSize - sizes.last()
        }
    }
}

@Composable
fun Item(
    modifier: Modifier = Modifier,
    id: String,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = id,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Preview
@Composable
private fun PreviewSolutionForAbove() {
    SolutionForAbove()
}

@Preview
@Composable
private fun PreviewNestedComponentScrollableInDifferentDirectionDoesNotThrowException() {
    NestedComponentScrollableInDifferentDirectionDoesNotThrowException()
}

@Preview
@Composable
private fun PreviewMultipleItemsInSingleItem() {
    MultipleItemsInSingleItem()
}

@Preview
@Composable
private fun PreviewCustomArrangement() {
    CustomArrangement()
}
