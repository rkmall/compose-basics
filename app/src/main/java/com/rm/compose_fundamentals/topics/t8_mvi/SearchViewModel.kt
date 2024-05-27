package com.rm.compose_fundamentals.topics.t8_mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SearchViewModel : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _items = MutableStateFlow(itemList)

    @OptIn(FlowPreview::class)
    val items = searchText
        .debounce(500)
        .onEach { _isSearching.update { true } } // searching is true when the searchText is changed
        .combine(_items) { text, item ->
            if (text.isBlank()) {
                item
            } else {
                delay(2000) // simulate network delay
                item.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } } // searching is false after the search is processed above
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            _items.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}


data class Item(
    val name: String,
    val description: String
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombination = listOf(
            name,
            description,
            "$name$description",
            "$name $description",
            "${name.first()} ${description.first()}",
        )

        return matchingCombination.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

private val itemList = listOf(
    Item(
        name = "TV",
        description = "Sony 45 inch television set"
    ),
    Item(
        name = "Calculator",
        description = "Casio Model T45 calculator"
    ),
    Item(
        name = "Yamaha HS80",
        description = "Yamaha full-range 80 watts speaker monitors"
    ),
    Item(
        name = "Google Pixel 8",
        description = "Google Pixel 8"
    ),
    Item(
        name = "Pukka Pad",
        description = "Pukka pad note book 200 pages"
    )
)