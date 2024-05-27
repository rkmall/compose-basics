package com.rm.compose_fundamentals.topics.t8_mvi

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun PreviewSearchTextField() {
    SearchTextField(viewModel = SearchViewModel())
}

@Composable
fun SearchTextField(viewModel: SearchViewModel) {
    val searchText by viewModel.searchText.collectAsState()
    val itemList by viewModel.items.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .systemBarsPadding()

        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = searchText  ,
                onValueChange = { newText -> viewModel.onSearchTextChange(newText) },
                placeholder = { Text(text = "Search") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (isSearching) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(itemList) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            text = "Name: ${it.name},\nDescription: ${it.description}",
                            fontSize = 24.sp
                        )
                    }
                }
            }
        }
    }
}
