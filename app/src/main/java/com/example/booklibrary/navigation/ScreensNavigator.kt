package com.example.booklibrary.navigation

import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ScreensNavigator {
    private val scope = CoroutineScope(Dispatchers.Main.immediate)
    private lateinit var parentNavController: NavHostController
    private lateinit var nestedNavController: NavHostController

    private var nestedNavControllerObserveJob: Job? = null
    private var parentNavControllerObserveJob: Job? = null

    val currentBottomTab = MutableStateFlow<BottomTab?>(null)
    val currentRoute = MutableStateFlow<Route?>(null)

    val isRootRoute = MutableStateFlow(false)

    fun setParentNavController(navController: NavHostController) {
        parentNavController = navController
        parentNavControllerObserveJob?.cancel()
        parentNavControllerObserveJob = scope.launch {
            navController.currentBackStackEntryFlow.map { backStackEntry ->
                val bottomTab = when (val routeName = backStackEntry.destination.route) {
                    Route.Dashboard.routeName -> BottomTab.Dashboard
                    Route.Requested.routeName -> BottomTab.Requested
                    Route.Borrowed.routeName -> BottomTab.Borrowed
                    Route.Profile.routeName -> BottomTab.Profile
                    null -> null
                    else -> throw RuntimeException("Unsupported bottom tab: $routeName")
                }
                currentBottomTab.value = bottomTab
            }.collect()
        }
    }

    fun setNestedNavController(navController: NavHostController) {
        nestedNavController = navController
        nestedNavControllerObserveJob?.cancel()
        nestedNavControllerObserveJob = scope.launch {
            navController.currentBackStackEntryFlow.map { backStackEntry ->
                val route = when (val routeName = backStackEntry.destination.route) {
                    //setup all the routes for the screens here
                    //also see for the nav args we need to pass for example book isbn
                    // and other args we might need
                    Route.Dashboard.routeName -> Route.Dashboard
                    Route.Requested.routeName -> Route.Requested
                    Route.Borrowed.routeName -> Route.Borrowed
                    Route.Profile.routeName -> Route.Profile
                    Route.SearchWithGoogle.routeName -> Route.SearchWithGoogle
                    null -> null
                    else -> throw RuntimeException("Unsupported route: $routeName")
                }
                currentRoute.value = route
                isRootRoute.value = route == Route.Dashboard
            }.collect()
        }
    }

    fun navigateBack() {
        if (!nestedNavController.popBackStack()) {
            parentNavController.popBackStack()
        }
    }

    fun toTab(bottomTab: BottomTab) {
        val route = when (bottomTab) {
            BottomTab.Dashboard -> Route.Dashboard
            BottomTab.Requested -> Route.Requested
            BottomTab.Borrowed -> Route.Borrowed
            BottomTab.Profile -> Route.Profile
        }
        parentNavController.navigate(route.routeName) {
            parentNavController.graph.startDestinationRoute?.let { startRoute ->
                popUpTo(startRoute) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun toRoute(route: Route) {
        nestedNavController.navigate(route.navCommand)
    }

    companion object {
        val BOTTOM_TABS = listOf(BottomTab.Dashboard, BottomTab.Requested, BottomTab.Borrowed, BottomTab.Profile)
    }
}