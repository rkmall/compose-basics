package com.rm.compose_fundamentals.topics.t7_components.navigation.navigationdrawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Preview
@Composable
fun NavigationDrawersPreview() {
    NavigationDrawerExample(rowItemsContent())
}

@Composable
fun NavigationDrawerExample(items: List<Pair<String, ImageVector>>) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                rowItemsContent().forEach {
                    RowItem(it)
                }
            }
        }
    ) {
        Scaffold(
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text("Show drawer") },
                    icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                    onClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )
            }
        ) { contentPadding ->
            Box(modifier = Modifier.padding(contentPadding)) { /* ... */ }
        }

    }
}

@Composable
fun RowItem(item: Pair<String, ImageVector>) {
    FilledTonalIconButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = {}
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                modifier = Modifier.padding(12.dp, 0.dp),
                imageVector = item.second ,
                contentDescription = item.first
            )
            Text(text = item.first)
        }
    }
}

fun rowItemsContent(): List<Pair<String, ImageVector>> {
    val items = arrayListOf<Pair<String, ImageVector>>()
    items.add(Pair("Email", Icons.Filled.Email))
    items.add(Pair("Add", Icons.Filled.AddCircle))
    items.add(Pair("Edit", Icons.Filled.Edit))
    items.add(Pair("Favourite", Icons.Filled.Favorite))
    return items
}