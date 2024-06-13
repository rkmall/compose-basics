package com.rm.compose_fundamentals.topics.t7_components.navigation.tablayout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rm.compose_fundamentals.topics.t7_components.navigation.bottomnavigation.usingbottomnavigation.BottomNavItem
import com.rm.compose_fundamentals.topics.t7_components.navigation.screen.Home
import com.rm.compose_fundamentals.topics.t7_components.navigation.screen.Notification
import com.rm.compose_fundamentals.topics.t7_components.navigation.screen.Post
import com.rm.compose_fundamentals.topics.t7_components.navigation.screen.Profile
import com.rm.compose_fundamentals.topics.t7_components.navigation.screen.Settings
import com.rm.compose_fundamentals.ui.theme.ComposefundamentalsTheme
import kotlinx.coroutines.launch

@Composable
fun TabScreen() {
    var tabIndex by remember { mutableIntStateOf(0) }
    
    val tabs = listOf(
        BottomNavItem.Home,
        BottomNavItem.Profile,
        BottomNavItem.Settings
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, navItem ->
                Tab(
                    text = { Text(text = navItem.title) },
                    selected = tabIndex == index ,
                    onClick = { tabIndex = index },
                    icon = { Icon(imageVector = navItem.icon, contentDescription = null) }
                )
            }
        }
        when(tabIndex) {
            0 -> Home()
            1 -> Profile()
            2 -> Settings()
        }
    }
}

@Preview
@Composable
private fun PreviewTabScreen() {
    TabScreen()
}


@Composable
fun ScrollableTabScreen() {
    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf(
        BottomNavItem.Home,
        BottomNavItem.Profile,
        BottomNavItem.Notification,
        BottomNavItem.Post,
        BottomNavItem.Settings
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScrollableTabRow(
            selectedTabIndex = tabIndex,
            backgroundColor = TabRowDefaults.containerColor,
            edgePadding = 8.dp
        ) {
            tabs.forEachIndexed { index, navItem ->
                Tab(
                    text = { Text(text = navItem.title) },
                    selected = tabIndex == index ,
                    onClick = { tabIndex = index },
                    icon = { Icon(imageVector = navItem.icon, contentDescription = null) }
                )
            }
        }
        when(tabIndex) {
            0 -> Home()
            1 -> Profile()
            2 -> Notification()
            3 -> Post()
            4 -> Settings()
        }
    }
}

@Preview
@Composable
private fun PreviewScrollableTabScreen() {
    ScrollableTabScreen()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeAbleTabScreen() {
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
            Tabs(tabs = tabs, pagerSate = pagerState)
            TabsContent(pagerSate = pagerState)
        }
    }
}

@Preview
@Composable
private fun PreviewSwipeAbleTabScreen() {
    SwipeAbleTabScreen()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(pagerSate: PagerState) {
    HorizontalPager(pagerSate ) { page ->
        when (page) {
            0 -> Home()
            1 -> Profile()
            2 -> Settings()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun PreviewTabsContent() {
    val tabs = listOf(
        BottomNavItem.Home,
        BottomNavItem.Profile,
        BottomNavItem.Settings
    )

    val pagerState = rememberPagerState(pageCount = { tabs.size })
    TabsContent(pagerSate = pagerState)
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

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun PreviewTabs() {
    val tabs = listOf(
        BottomNavItem.Home,
        BottomNavItem.Profile,
        BottomNavItem.Settings
    )
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    Tabs(tabs, pagerState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    topBarName: String
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFFFF9C4)
        ),
        title = {
            Text(
                text = topBarName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    )
}

@Preview
@Composable
private fun PreviewTopBar() {
    TopBar("TabLayout example")
}






