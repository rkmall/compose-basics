package com.rm.compose_fundamentals.topics.t2_layouts.constraintlayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

@Preview
@Composable
fun PreviewComposeConstraintLayout() {
    ComposeConstraintLayout()
}

@Composable
fun ComposeConstraintLayout() {
    val constraints = ConstraintSet {
        val cyanBox = createRefFor("cyan")
        val yellowBox = createRefFor("yellow")

        constrain(cyanBox) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(yellowBox) {
            top.linkTo(parent.top)
            start.linkTo(cyanBox.end)
            end.linkTo(parent.end)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }
        createHorizontalChain(cyanBox, yellowBox, chainStyle = ChainStyle.Spread)
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize(),
        constraintSet = constraints,
    ) {
        Box(
            modifier = Modifier
                .background(Color.Cyan)
                .layoutId("cyan")
        )
        Box(
            modifier = Modifier
                .background(Color.Yellow)
                .layoutId("yellow")
        )
    }
}
