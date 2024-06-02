package com.rm.compose_fundamentals.topics.t7_components.navigation.bottomsheetnavigation.usingbottombar


sealed class BottomNavRoute(val route: String) {
    data object Home : BottomNavRoute("home")
    data object Notification : BottomNavRoute("notification")
    data object Post : BottomNavRoute("post")
    data object Profile : BottomNavRoute("profile")
    data object Settings : BottomNavRoute("settings")
}