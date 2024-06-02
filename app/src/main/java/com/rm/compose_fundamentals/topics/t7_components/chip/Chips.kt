package com.rm.compose_fundamentals.topics.t7_components.chip

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PreviewAssistChip() {
    AssistChipExample()
}

@Preview
@Composable
fun PreviewFilterChip() {
    FilterChipExample()
}

@Preview
@Composable
fun PreviewInputChip() {
    InputChipExample()
}



@Composable
fun AssistChipExample(onClick: () -> Unit = {}) {
    AssistChip(
        onClick = { onClick() },
        label = { Text(text = "Assist Chip") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Settings" )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipExample() {
    var selected by remember { mutableStateOf(false) }
    FilterChip(
        selected = selected,
        onClick = { selected = !selected },
        label = { Text(text = "Filter Chip") },

        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done",
                )
            }
        } else null,

        colors = if (selected) {
            FilterChipDefaults.filterChipColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        } else FilterChipDefaults.filterChipColors()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputChipExample() {
    var enable: Boolean by remember { mutableStateOf(true) }

    InputChip(
        selected = enable,
        onClick = { enable = !enable },
        label = { Text(text = "Input Chip") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Done",
            )
        },
        trailingIcon = if (enable) {
            {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Done",
                )
            }
        } else {
            {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close",
                )
            }
        }
    )
}