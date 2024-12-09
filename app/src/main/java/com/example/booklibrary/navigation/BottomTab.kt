package com.example.booklibrary.navigation

import com.example.booklibrary.R

sealed class BottomTab(
    val route: String,
    var title: String,
    var icon: Int
) {
    object Home : BottomTab(
        "HOME",
        "Home",
        R.drawable.home
    )

    object Requested : BottomTab(
        "REQUESTED",
        "Requested",
        R.drawable.recommended
    )

    object Borrowed : BottomTab(
        "BORROWED",
        "Borrowed",
        R.drawable.history_icon
    )

    object Profile : BottomTab(
        "PROFILE",
        "Profile",
        R.drawable.profile
    )
}