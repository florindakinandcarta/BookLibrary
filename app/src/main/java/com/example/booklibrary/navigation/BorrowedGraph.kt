package com.example.booklibrary.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.booklibrary.ui.borrowedHistory.BookCheckoutsScreen
import com.example.booklibrary.ui.generalScreens.SearchScreen
import com.example.booklibrary.ui.returnDialog.ReturnDialog


fun NavGraphBuilder.borrowedGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.BORROWED,
        startDestination = BorrowedScreen.Borrowed.route
    ){
        composable(BorrowedScreen.Borrowed.route){
            BookCheckoutsScreen(
                onBorrowedBookClick = {
                    navHostController.navigate(BorrowedScreen.DetailsScreen.route)
                },
                onSearchClick = {},
                onReturnClick = {},
            )
        }
        composable(BorrowedScreen.DetailsScreen.route){
            //navigate to book details
        }
        composable(BorrowedScreen.ReturnDialog.route){
            ReturnDialog(
                onNext = {
                    //return the book viewmodel call
                }
            )
        }
        composable(BorrowedScreen.SearchBorrowed.route){
            SearchScreen(
                onClickedBook = {},
                onScanClick = {},
                onBackClicked = {},
                onSearchClick = { bookISBN ->

                }
            )
        }
    }
}

sealed class BorrowedScreen(val route: String){
    object Borrowed: BorrowedScreen("BORROWED")
    object DetailsScreen: BorrowedScreen("DETAILS")
    object ReturnDialog: BorrowedScreen("RETURN")
    object SearchBorrowed: BorrowedScreen("SEARCHBORROWED")
}