package com.example.booklibrary.ui.home

import androidx.compose.foundation.layout.RowScope
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
import com.example.booklibrary.navigation.BottomNavItem

@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Dashboard,
        BottomNavItem.Requested,
        BottomNavItem.Borrowed,
        BottomNavItem.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar {
        items.forEach { item ->
            AddItem(
                screen = item,
                isSelected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    NavigationBarItem(
        selected = isSelected,
        label = {
            Text(
                text = screen.title,
            )
        },
        onClick = onClick,
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