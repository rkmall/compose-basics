package com.rm.compose_fundamentals.topics.t2_layouts.lazylayouts

import androidx.annotation.DrawableRes
import com.rm.compose_fundamentals.R

fun getItemData(): List<Guitar> {
    return listOf(
        Guitar(
            1,
            R.drawable.fender,
            "Fender",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            2,
            R.drawable.gibson,
            "Gibson",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            3,
            R.drawable.ibanez,
            "Ibanez",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            4,
            R.drawable.prs,
            "PRS",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            5,
            R.drawable.jackson,
            "Jackson",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            6,
            R.drawable.esp,
            "ESP",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            7,
            R.drawable.musicman,
            "Musicman",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        ),
        Guitar(
            8,
            R.drawable.charvel,
            "Charvel",
            "Guitar description for testing. Max lines of text 2 and on text overflow ellipses"
        )
    )
}

data class Guitar(
    val id: Int,
    @DrawableRes val drawable: Int,
    val name: String,
    val description: String
)