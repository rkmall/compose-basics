package com.rm.compose_fundamentals.topics.t6_navigation.cupcake_example.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rm.compose_fundamentals.R

@Composable
fun FormattedPriceLabel(subTotal: String, modifier: Modifier) {
    Text(
        text = stringResource(id = R.string.subtotal_price, subTotal),
        modifier = modifier,
        style = MaterialTheme.typography.headlineSmall
    )
}