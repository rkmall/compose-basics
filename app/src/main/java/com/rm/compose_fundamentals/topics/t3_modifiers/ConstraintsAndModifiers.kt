package com.rm.compose_fundamentals.topics.t3_modifiers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rm.compose_fundamentals.R

@Preview
@Composable
private fun PreviewExample1() {
    Example1()
}

@Preview
@Composable
private fun PreviewExample2() {
    Example2()
}

@Preview
@Composable
private fun PreviewExample3() {
    Example3()
}

@Composable
private fun Example1() {
    Image(
        painter = painterResource(id = R.drawable.img),
        contentDescription = null,
        modifier = Modifier
            // sets min width and height to max value
            .fillMaxSize()
            // size has no effect even if it wants to set it to 50.dp since it still
            // needs to adhere to the incoming constraint ignoring the value provided
            .size(10.dp)
    )
}

@Composable
private fun Example2() {
    Image(
        painter = painterResource(id = R.drawable.img),
        contentDescription = null,
        modifier = Modifier
            // sets min width and height to max value
            .fillMaxSize()
            // resets the min constraints, so while fillMaxSize resulted in fixed
            // constraints, wrapContentSize resets it back to bounded constraints.
            // Now, the node can take up whole space again or be smaller than the
            // entire space.
            // It also takes the child and puts it in the center of the available
            // min bounds that were passed in and communicates the min bounds that
            // were passed into it.
            .wrapContentSize()
            // size modifier sets the constraints to min and max bounds of 50
            .size(50.dp)
    )
}

@Composable
private fun Example3() {
    Image(
        painter = painterResource(id = R.drawable.img),
        contentDescription = null,
        modifier = Modifier
            // clip doesn't modify constraints
            .clip(CircleShape)
            // padding lowers lowers max constrains
            .padding(10.dp)
            // size sets the constraints 100 by 100 dp
            .size(100.dp)
    )
}
