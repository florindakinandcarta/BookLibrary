package com.example.booklibrary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.booklibrary.data.book.viewModels.BookViewModel
import com.example.booklibrary.ui.home.HomeScreen
import kotlinx.coroutines.launch

@Composable
fun HomeNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.HOME,
        startDestination = BottomTab.Home.route
    ) {
        composable(route = BottomTab.Home.route) {
            val bookViewModel: BookViewModel = hiltViewModel()
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    bookViewModel.getAllBooks()
                }
            }
            HomeScreen(
                onSearchClick = {},
                onClickedBook = {
                },
                onNotificationClick = {},
                onSelectedLanguageClick = { language ->
                    scope.launch {
                        bookViewModel.getBooksByLanguage(language)
                    }
                }
            )
        }
        composable(route = BottomTab.Requested.route) {
            navHostController.navigate(Graph.REQUESTED)
        }
        composable(route = BottomTab.Borrowed.route) {
            navHostController.navigate(Graph.BORROWED)
        }
        composable(route = BottomTab.Profile.route) {
            navHostController.navigate(Graph.PROFILE)
        }
        requestedGraph(navHostController = navHostController)
        borrowedGraph(navHostController = navHostController)
        profileGraph(navHostController = navHostController)
    }
}