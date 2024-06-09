package com.rm.compose_fundamentals.topics.t7_components.lazylist


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rm.compose_fundamentals.R

@Preview
@Composable
fun PreviewSimpleList() {
    SimpleColumnList()
}

@Preview
@Composable
fun PreviewLazyList1() {
    LazyListExample1()
}

@Preview
@Composable
fun PreviewLazyList2() {
    LazyListExample2()
}

@Preview
@Composable
fun PreviewLazyList3() {
    LazyListExample3()
}

@Preview
@Composable
fun PreviewLazyList4() {
    LazyListMultipleViewTypes()
}

@Preview
@Composable
fun PreviewLazyList5() {
    GuitarList(guitars)
}

// Only suitable for small number of items.
@Composable
fun SimpleColumnList() {
    val scrollableState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollableState)
    ) {
        for (i in 0..50) {
            RowItem(item = "Item: $i")
        }
    }
}

/**
 * LazyColumn and LazyRow only compose and layout items
 * which are visible in the device viewport.
 *
 *  - The LazyListScope block provides DSL which allows apps to describe
 *   items content.
 *
 * - The Lazy component is then responsible for adding each item's content
 *   as required by the layout and scroll position.
 *
 * LazyListScope DSL:
 *  - LazyListScope DSL provides a number of functions for describing items
 *    in the layout. The most basic ones are:
 *      - item() for adding single item.
 *      - items(Int) for adding multiple items.
 *      - items(List) for adding multiple items.
 */
@Composable
fun LazyListExample1() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(50) {
            RowItem(item = "Item: $it")
        }
    }
}

@Composable
fun LazyListExample2( ) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // items provide LazyItemScope where we can specify the view to display for each item
        // just like RecyclerView.ViewHolder
        items(
            items =  messages,
            key = { message -> message.id }
        ) { message ->
            RowItem(item = message.content)
        }
    }
}

@Composable
fun LazyListExample3( ) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // itemsIndexed is just like forEachIndexed that provides access to index
        itemsIndexed(messages) { index, message ->
            if (index % 2 == 0) {
                RowItem(
                    item = "Index: $index, ${message.content}",
                    color = Color.Green
                )
            } else {
                RowItem(
                    item = "Index: $index, ${message.content}",
                    color = Color.Yellow
                )
            }

        }
    }
}

@Composable
fun LazyListMultipleViewTypes() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            RowItem(item = "TITLE")
        }

        items(5) {
            RowItem(item = "Item: $it")
        }

        itemsIndexed(listOf("apple", "ball", "cat")) { index, string ->
            RowItem(item = "Index: $index, Item: $string")
        }
    }
}

@Composable
fun RowItem(
    item: String,
    color: Color = Color.Cyan
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(color)
            .border(1.dp, Color.Black),
        text = item,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

class Message(
    val id: Int,
    val content: String
)

fun getItems(): List<Message> = List(50) { Message(it,"Message: $it") }

val messages = getItems()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuitarList(guitars: List<Guitar>) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text(text = "Guitars") },
            )
        },
    ) {  innerPaddings ->
        LazyColumn(
            modifier = Modifier.padding(innerPaddings)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 25.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "\uD83C\uDFB8 Guitar of the world",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
            items(guitars) { guitar ->
                GuitarCard(guitar.name, guitar.description, guitar.imgRes)
            }
        }
    }
}

@Composable
fun GuitarCard(
    name: String,
    description: String,
    imgRes: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = imgRes) ,
                contentDescription = "Guitar",
                modifier = Modifier
                    .size(130.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = description,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

val guitars = listOf(
    Guitar(
        1,
        "Fender",
        "Classic player 60s. World renowned guitar. Played by the legends.",
        R.drawable.fender
    ),
    Guitar(
        2,
        "Gibson",
        "World renowned legendary guitar. Killer tone made popular by the legends.",
        R.drawable.gibson
    ),
    Guitar(
        3,
        "Ibanez",
        "Modern machine. Dynamic tone, fierce machine with on point precision and playability.",
        R.drawable.ibanez
    )
)

data class Guitar(
    val id: Int,
    val name: String,
    val description: String,
    val imgRes: Int
)

