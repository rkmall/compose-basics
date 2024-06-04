package com.rm.compose_fundamentals.topics.t7_components.customcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun PreviewHorizontalSimpleList() {
    HorizontalSimpleList(list = getCountries())
}

@Composable
fun HorizontalSimpleList(list: List<Country>) {
    LazyRow(
        modifier = Modifier.wrapContentHeight()
    ) {
        items(list) { item ->
            Text(text = item.name, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}

@Preview
@Composable
fun PreviewVerticalSimpleList() {
    VerticalSimpleList(list = getCountries())
}


@Composable
fun VerticalSimpleList(list: List<Country>) {
    LazyColumn(
        modifier = Modifier.wrapContentHeight()
    ) {
        items(list) { item ->
            Text(text = item.name, fontWeight = FontWeight.Bold)
            CustomSpacer(height = 3.dp)
        }
    }
}


@Preview
@Composable
fun PreviewHorizontalSimpleGridList() {
    SimpleHorizontalGridList(list = listOf(
            "app", "web", "desktop", "a", "b", "c", "x", "y", "z"
        )
    )
}


@Composable
fun SimpleVerticalGridList(list: List<String>, numOfColumns: Int = 3) {
    LazyVerticalGrid(
        GridCells.Fixed(numOfColumns),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(15.dp)
    ) {
        items(list) { item ->
            Box(
                modifier = Modifier
                    .background(color = Color.Blue)
                    .height(50.dp)
                    .padding(50.dp)
            ) {
                Text(text = item, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun SimpleHorizontalGridList(list: List<String>, numOfRows: Int = 3) {
    LazyHorizontalGrid(
        GridCells.Fixed(numOfRows),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(15.dp)
    ) {
        items(list) { item ->
            Box(
                modifier = Modifier
                    .padding(15.dp)
                    .background(color = Color.Magenta)
                    .size(50.dp)
            ) {
                Text(text = item, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun CustomVerticalList(list: List<Country>) {
    val listOfItem = remember { mutableStateListOf<Country>() }
    var openDialog by remember { mutableStateOf(false) }
    var position = 0
    list.forEach {
        listOfItem.add(it)
    }
    LazyColumn(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        itemsIndexed(listOfItem) { index, item ->
            CardTextWithIcon(item, onRemoveClicked = {
                openDialog = true
                position = index
            })
            if (openDialog) {
                DeleteDialog(onConfirmClicked = {
                    openDialog = false
                    listOfItem.removeAt(position)
                })
            }
        }
    }
}


data class Country(val name: String)

fun getCountries() = listOf(
    Country("USA"),
    Country("UK"),
    Country("India"),
    Country("Vietnam"),
    Country("Korea"),
    Country("Mexico"),
    Country("Germany"),
    Country("Canada"),
    Country("Australia"),
    Country("Thailand"),
    Country("Singapore"),
    Country("China")
)

