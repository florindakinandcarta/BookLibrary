package com.example.booklibrary.ui.home

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.booklibrary.navigation.BottomTab

@Composable
fun MyBottomTabsBar(
    bottomTabs: List<BottomTab>,
    currentBottomTab: BottomTab?,
    onTabClicked: (BottomTab) -> Unit,
) {
    NavigationBar {
        bottomTabs.forEachIndexed { _, bottomTab ->
            NavigationBarItem(
                alwaysShowLabel = true,
                selected = currentBottomTab == bottomTab,
                label = {
                    Text(
                        text = bottomTab.title,
                    )
                },
                onClick = { onTabClicked(bottomTab) },
                icon = {
                    Icon(
                        painterResource(id = bottomTab.icon),
                        contentDescription = bottomTab.title
                    )
                })
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomTab,
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