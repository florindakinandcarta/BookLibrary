package com.example.booklibrary.navigation

import com.example.booklibrary.R

sealed class BottomNavItem(
    val route: String,
    var title: String,
    var icon: Int
) {
    object Dashboard : BottomNavItem(
        "dashboard",
        "Dashboard",
        R.drawable.home
    )

    object Requested : BottomNavItem(
        "requested",
        "Requested",
        R.drawable.recommended
    )

    object Borrowed : BottomNavItem(
        "history",
        "History",
        R.drawable.history_icon
    )

    object Profile : BottomNavItem(
        "profile",
        "Profile",
        R.drawable.profile
    )
}