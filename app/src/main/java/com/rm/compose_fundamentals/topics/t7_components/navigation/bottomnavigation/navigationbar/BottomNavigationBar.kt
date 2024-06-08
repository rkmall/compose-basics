package com.rm.compose_fundamentals.topics.t7_components.navigation.bottomnavigation.navigationbar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.rm.compose_fundamentals.topics.t7_components.navigation.bottomnavigation.usingbottomnavigation.BottomNavItem


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            AppBottomNavigation(
                modifier = Modifier.padding(top = 8.dp),
                navController
            )
        }
    ) {
        AppNavGraph(navController = navController)
    }
}

@Composable
fun AppNavGraph(navController: NavHostController) {
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
fun AppBottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val navigationItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Notification,
        BottomNavItem.Post,
        BottomNavItem.Profile,
        BottomNavItem.Settings
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navigationItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon ,
                        contentDescription = item.title
                    )
                },
                selected = currentRoute == item.route,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    unselectedIconColor = Color.Black.copy(alpha = 0.4f)
                ),
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
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewAppScreen() {
    AppScreen()
}

@Preview
@Composable
fun PreviewAppBottomNavigation() {
    AppBottomNavigation(
        Modifier.padding(top = 16.dp),
        rememberNavController()
    )
}