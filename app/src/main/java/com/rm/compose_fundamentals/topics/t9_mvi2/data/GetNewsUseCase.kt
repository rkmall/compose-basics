package com.rm.compose_fundamentals.topics.t9_mvi2.data

class GetNewsUseCase {

    operator fun invoke() : List<News> {
        return listOf(
            News(
                id = 1,
                title = "News title One",
                summary = "News One summary",
                date = "10-10-2020"
            ),
            News(
                id = 2,
                title = "News title Two",
                summary = "News Two summary",
                date = "11-10-2020"
            ),
            News(
                id = 3,
                title = "News title Three",
                summary = "News Three summary",
                date = "12-10-2020"
            ),
            News(
                id = 4,
                title = "News title Four",
                summary = "News Four summary",
                date = "13-10-2020"
            ),
            News(
                id = 5,
                title = "News title Five",
                summary = "News Five summary",
                date = "14-10-2020"
            )
        )
    }

}