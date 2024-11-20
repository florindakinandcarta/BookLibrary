package com.example.booklibrary.ui.home

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.booklibrary.navigation.BottomTab

@Composable
fun MyBottomTabsBar(navHostController: NavHostController) {
    val screens = listOf(
        BottomTab.Home,
        BottomTab.Requested,
        BottomTab.Borrowed,
        BottomTab.Profile
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar {
        screens.forEach { screen ->
            NavigationBarItem(
                label = {
                    Text(
                        text = screen.title,
                    )
                },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navHostController.navigate(screen.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navHostController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                    }
                },
                icon = {
                    Icon(
                        painterResource(id = screen.icon),
                        contentDescription = screen.title
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.run {
                    colors(
                        selectedIconColor = Color(0xFF834EFF),
                        unselectedIconColor = Color(0xFFA783DF),
                        selectedTextColor = Color(0xFF834EFF),
                        unselectedTextColor = Color(0xFFFFFFFF),
                    )
                }

            )
        }
    }
}