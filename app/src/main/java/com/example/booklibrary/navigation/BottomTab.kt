package com.example.booklibrary.navigation

import com.example.booklibrary.R

sealed class BottomTab(
    val route: String,
    var title: String,
    var icon: Int
) {
    data object Dashboard : BottomTab(
        "dashboard",
        "Dashboard",
        R.drawable.home
    )

    data object Requested : BottomTab(
        "requested",
        "Requested",
        R.drawable.recommended
    )

    data object Borrowed : BottomTab(
        "history",
        "History",
        R.drawable.history_icon
    )

    data object Profile : BottomTab(
        "profile",
        "Profile",
        R.drawable.profile
    )
}