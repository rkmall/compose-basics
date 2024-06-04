package com.rm.compose_fundamentals.topics.t7_components.customcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.RadioButton
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun PreviewRadioButtons() {
    RadioButtons(options = listOf("apple", "ball", "cat"), currentOption = "apple") {}
}


@Composable
fun RadioButtons(
    options: List<String>,
    currentOption: String,                                      // this does a string match with the options
    modifier: Modifier = Modifier,
    textTemplate: @Composable (String) -> Unit = {
        Text(it, color = MaterialTheme.colorScheme.onSurface)
    },
    onSelect: (Int) -> Unit                                     // this outputs the index chosen
) {
    @Composable
    fun RadioItem(
        option: Pair<Int, String>,
        isSelected: Boolean,
        textTemplate: @Composable (String) -> Unit,
        onSelect: (Int) -> Unit
    ) {
        Button(
            { onSelect(option.first) },
            shape = RectangleShape,
            colors = ButtonDefaults.textButtonColors()
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = isSelected, onClick = { onSelect(option.first) })
                textTemplate(option.second)
            }
        }
    }

    Column(
        Modifier
            .width(IntrinsicSize.Max)
            .background(androidx.compose.material.MaterialTheme.colors.surface)
            .then(modifier)
    ) {
        options.forEachIndexed { i,v ->
            RadioItem(
                Pair(i, v),
                currentOption == v,
                textTemplate,
                onSelect
            )
        }
    }
}