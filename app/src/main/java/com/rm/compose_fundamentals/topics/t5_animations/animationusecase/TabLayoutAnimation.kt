package com.rm.compose_fundamentals.topics.t5_animations.animationusecase

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Down
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Up
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rm.compose_fundamentals.topics.t7_components.navigation.bottomnavigation.usingbottomnavigation.BottomNavItem
import com.rm.compose_fundamentals.topics.t7_components.navigation.screen.Home
import com.rm.compose_fundamentals.topics.t7_components.navigation.screen.Profile
import com.rm.compose_fundamentals.topics.t7_components.navigation.screen.Settings
import com.rm.compose_fundamentals.topics.t7_components.navigation.tablayout.TopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayoutAnimation() {
    val tabs = listOf(
        BottomNavItem.Home,
        BottomNavItem.Profile,
        BottomNavItem.Settings
    )

    val pagerState = rememberPagerState(pageCount = { tabs.size })

    Scaffold(
        topBar = { TopBar("TabLayout example") }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {
            Tabs(tabs, pagerState)
            TabsContent(pagerState)
        }
    }
}

@Preview
@Composable
private fun PreviewTabLayoutAnimation() {
    TabLayoutAnimation()
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun TabsContent(pagerSate: PagerState) {
    HorizontalPager(pagerSate) { page ->
        AnimatedContent(
            targetState = page,
            transitionSpec = {
                slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = Up
                ).togetherWith(
                    slideOutOfContainer(
                        animationSpec = tween(300, easing = EaseOut),
                        towards = Down
                    )
                )
            },
            label = "TabAnimation"
        ) {targetState ->
            when (targetState) {
                0 -> Home()
                1 -> Profile()
                2 -> Settings()
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(tabs: List<BottomNavItem>, pagerSate: PagerState) {
    val scope = rememberCoroutineScope()

    TabRow(selectedTabIndex = pagerSate.currentPage) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = pagerSate.currentPage == index,
                text = { Text(text = tab.title) },
                icon = { Icon(imageVector = tab.icon, contentDescription = null) },
                onClick = {
                    scope.launch {
                        pagerSate.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}