package com.rm.compose_fundamentals.topics.t7_components.examples

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FABExample(onClick: () -> Unit = {}) {
    FloatingActionButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "FAB Add"
        )
    }
}

@Composable
fun SmallFABExample(onClick: () -> Unit = {}) {
    SmallFloatingActionButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "FAB Add"
        )
    }
}

@Composable
fun LargeFABExample(onClick: () -> Unit = {}) {
    LargeFloatingActionButton(
        onClick = onClick,
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "FAB Add"
        )
    }
}

@Composable
fun ExtendedFABExample(onClick: () -> Unit = {}) {
    ExtendedFloatingActionButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = "FAB Add"
        )
        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = "Extended FAB")
    }
}

@Preview
@Composable
fun FABPreview() {
    FABExample()
}

@Preview
@Composable
fun SmallFABPreview() {
    SmallFABExample()
}

@Preview
@Composable
fun LargeFABPreview() {
    LargeFABExample()
}

@Preview
@Composable
fun ExtendedFABPreview() {
    ExtendedFABExample()
}
