package com.rm.compose_fundamentals.topics.t8_composition_local

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun CompositionLocalExample() {
    MaterialTheme {
        Column(
            Modifier
                .fillMaxWidth()
        ) {
            Text("Uses MaterialTheme's provided alpha")

            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)) {
                Text("Uses LocalContentAlpha medium value")

                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)) {
                    DescendantExample()
                }
            }
        }
    }
}

@Composable
fun DescendantExample() {
    Text("Uses LocalContentAlpha disabled value")
}

@Composable
fun SomeText(size: Int) {
    val resources = LocalContext.current.resources
    val number = remember(resources, size) {
        resources.getInteger(size)
    }
    Text(text = number.toString())
}

@Preview
@Composable
fun CompositionLocalPreview() {
    CompositionLocalExample()
}