package com.example.booklibrary.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.booklibrary.ui.home.MyBottomTabsBar

@Composable
fun MainScreen(navHostController: NavHostController = rememberNavController()) {
    Scaffold(bottomBar = {
        MyBottomTabsBar(
            navHostController = navHostController
        )
    }) {
        it
        HomeNavGraph(navHostController = navHostController)
    }
}