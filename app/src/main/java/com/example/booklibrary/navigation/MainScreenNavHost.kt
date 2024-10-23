package com.example.booklibrary.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreenNavHost() {
    val mainNavHostController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navHostController = mainNavHostController)
        }
    ) { paddingValues ->
        NavHost(
            navController = mainNavHostController,
            startDestination = BottomTab.Dashboard.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            dashboardGraph(navHostController = mainNavHostController)
            requestedGraph(navHostController = mainNavHostController)
            borrowedGraph(navHostController = mainNavHostController)
            profileGraph(navHostController = mainNavHostController)
        }
    }
}