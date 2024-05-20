package com.rm.compose_fundamentals.topics.t1_composition_functions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

// Compose functions can be as dynamic as Kotlin code
@Composable
fun Greet(name: String) {
    Text(
        text = "Hello $name",
        fontSize = 30.sp
    )
}

@Composable
fun GreetAll(names: List<String>) {
    for (name in names) {
        Text(
            text = "Hello $name",
            fontSize = 30.sp
        )
    }
}

// Compose function doesn't execute in order.
// The function inside MyNav can execute in any order and also in parallel.
private object ComposeFunctionsDoNotExecuteInOrder {
    @Composable
    fun ButtonRow() {
        MyNav {
            StartScreen()   // these functions can execute in any order and in parallel
            MiddleScreen()
            EndScreen()
        }
    }

    @Composable
    fun MyNav(content: @Composable () -> Unit) {}

    @Composable
    fun StartScreen() {}

    @Composable
    fun MiddleScreen() {}

    @Composable
    fun EndScreen() {}
}

// Code with no side-effect
@Composable
fun ListComposable(myList: List<String>) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            for (item in myList) {
                Text("Item: $item")
            }
        }
        Text("Count: ${myList.size}")
    }
}

// Code with side-effect
@Composable
fun ListWithBug(myList: List<String>) {
    var items = 0   // local var
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            for (item in myList) {
                Text("Item: $item")
                items++ // side-effect of the column recomposing, avoid it
            }
        }
        Text("Count: ${myList.size}")
    }
}

// Recomposition skips as many as composable function and lambdas as possible.
// Display a list of names the user can click with a header.
@Composable
fun NamePicker(
    header: String,
    names: List<String>,
    onNameClicked: (String) -> Unit
) {
    Column {
        // this will recompose when [header] changes, but not when [names] changes
        Text(header, style = MaterialTheme.typography.bodyLarge)
        Divider()

        // LazyColumn is the Compose version of a RecyclerView.
        // The lambda passed to items() is similar to a RecyclerView.ViewHolder.
        LazyColumn {
            items(names) { name ->
                // When an item's [name] updates, the adapter for that item
                // will recompose. This will not recompose when [header] changes
                NamePickerItem(name, onNameClicked)
            }
        }
    }
}

// Display a single name the user can click.
@Composable
private fun NamePickerItem(name: String, onClicked: (String) -> Unit) {
    Text(name, Modifier.clickable(onClick = { onClicked(name) }))
}



