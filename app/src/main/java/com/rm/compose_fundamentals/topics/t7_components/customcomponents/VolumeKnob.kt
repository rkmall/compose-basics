package com.rm.compose_fundamentals.topics.t7_components.customcomponents

import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rm.compose_fundamentals.R
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt

@Preview
@Composable
fun PreviewVolumeKnobWithBar() {
    VolumeKnobWithBar()
}

@Preview
@Composable
fun PreviewVolumeKnob() {
    VolumeKnob {}
}

@Preview
@Composable
fun PreviewVolumeBar() {
    VolumeBar()
}


@Composable
fun VolumeKnobWithBar() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
                .padding(30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            var volume by remember { mutableStateOf(0f) }
            val barCount = 20

            VolumeKnob(
                modifier = Modifier
                    .size(100.dp)
            ) {
                volume = it
            }
            Spacer(modifier = Modifier.width(20.dp))

            VolumeBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                activeBars = (barCount * volume).roundToInt(),
                barCount = barCount
            )
        }
    }
}

/**
 * touchY = (0,y)       * (touchX, touchY) = point where we touch to rotate
 *                    * *
 *                  *   *
 *                *     *
 *              *       *   OPP Side
 *            *         *
 *          * Theta     *
 *        * * * * * * * *  touchX = (x,0)
 *           ADJ Side
 *
 *  We know,
 *        tanTheta = OPP / ADJ
 *        Theta = arcTan(OPP/ADJ)
 *
 *  We know,
 *      1 radian = (180 / PI) deg
 *      1 deg = (PI / 180) radian
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VolumeKnob(
    modifier: Modifier = Modifier,
    limitingAngle: Float = 25f,
    onValueChange: (Float) -> Unit
) {
    var rotation by remember { mutableFloatStateOf(limitingAngle) }

    var touchX by remember { mutableFloatStateOf(0f) }
    var touchY by remember { mutableFloatStateOf(0f) }
    var centerX by remember { mutableFloatStateOf(0f) }
    var centerY by remember { mutableFloatStateOf(0f) }

    Image(
        painter = painterResource(id = R.drawable.volknob),
        contentDescription = "Volume Knob",
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val windowBounds = it.boundsInWindow()
                centerX = windowBounds.size.width / 2f      // div by 2f to get center
                centerY = windowBounds.size.height / 2f     // div by 2f to get center

            }
            .pointerInteropFilter { touchEvent ->
                touchX = touchEvent.x
                touchY = touchEvent.y

                val adjacentSide = centerX - touchX
                val oppositeSide = centerY - touchY
                val angle = -atan2(adjacentSide, oppositeSide) * (180f / PI).toFloat()

                when (touchEvent.action) {
                    MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                        if (angle !in -limitingAngle..limitingAngle) {
                            val fixedAngle = if (angle in -180f..-limitingAngle) {
                                angle + 360f
                            } else {
                                angle
                            }
                            rotation = fixedAngle

                            val percent = (fixedAngle - limitingAngle) / (360f - 2 * limitingAngle)
                            Log.d("angle", "$angle : $percent")
                            onValueChange(percent)
                            true
                        } else {
                            false
                        }
                    }

                    else -> false
                }
            }
            .rotate(rotation),
    )
}

@Composable
fun VolumeBar(
    modifier: Modifier = Modifier,
    activeBars: Int = 0 ,
    barCount: Int = 10
) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        // If the barCount = 20, barWidth will be of size able to fit 40 bars
        val barWidth = remember { constraints.maxWidth / (2f * barCount) }

        Canvas(modifier = modifier) {
            for (i in 0 until barCount)
                drawRoundRect(
                    color = if (i in 0..activeBars) Color.Green else Color.DarkGray,
                    topLeft = Offset(x = i * barWidth * 2f + barWidth/2f, y = 0f),
                    size = Size(barWidth, constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(0f)
                )
        }
    }
}
