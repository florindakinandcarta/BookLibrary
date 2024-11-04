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
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
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
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any{
        it.route == currentDestination?.route
    }
    if (bottomBarDestination){
            NavigationBar {
                screens.forEach { screen ->
                    AddItem(
                        screen = screen,
                        currentDestination = currentDestination,
                        navController = navHostController
                    )
                }
            }
    }

}

@Composable
fun RowScope.AddItem(
    screen: BottomTab,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(
                text = screen.title,
            )
        },
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
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