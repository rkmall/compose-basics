package com.rm.compose_fundamentals.topics.t7_components.navigation.bottomnavigation.usingbottomnavigation

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rm.compose_fundamentals.topics.t7_components.navigation.bottomnavigation.screen.Home
import com.rm.compose_fundamentals.topics.t7_components.navigation.bottomnavigation.screen.Notification
import com.rm.compose_fundamentals.topics.t7_components.navigation.bottomnavigation.screen.Post
import com.rm.compose_fundamentals.topics.t7_components.navigation.bottomnavigation.screen.Profile
import com.rm.compose_fundamentals.topics.t7_components.navigation.bottomnavigation.screen.Settings
import com.rm.compose_fundamentals.topics.t7_components.navigation.bottomnavigation.usingbottomappbar.BottomNavRoute

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            MBottomNavigation(navController = navController)
        }
    ) {
        MNavGraph(navController)
    }
}

@Composable
fun MNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavRoute.Home.route
    ) {
        composable(BottomNavRoute.Home.route) { Home() }
        composable(BottomNavRoute.Notification.route) { Notification() }
        composable(BottomNavRoute.Post.route) { Post() }
        composable(BottomNavRoute.Profile.route) { Profile() }
        composable(BottomNavRoute.Settings.route) { Settings() }
    }
}

@Composable
fun MBottomNavigation(navController: NavController) {
    val navigationItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Notification,
        BottomNavItem.Post,
        BottomNavItem.Profile,
        BottomNavItem.Settings
    )

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navigationItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                       Icon(imageVector = item.icon, contentDescription = item.title)
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.5f),
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}

@Preview
@Composable
fun PreviewBottomNavigation() {
    MBottomNavigation(rememberNavController())
}
