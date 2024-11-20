package com.example.booklibrary.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.booklibrary.data.book.models.request.BookReturnRequest
import com.example.booklibrary.data.book.viewModels.BookCheckoutViewModel
import com.example.booklibrary.data.book.viewModels.BookItemViewModel
import com.example.booklibrary.data.book.viewModels.BookViewModel
import com.example.booklibrary.ui.borrowedHistory.BookCheckoutsScreen
import com.example.booklibrary.ui.borrowedHistory.BorrowedSearchScreen
import com.example.booklibrary.ui.generalScreens.GeneralBookDetails
import com.example.booklibrary.ui.returnDialog.ReturnDialog
import com.example.booklibrary.util.Resource
import kotlinx.coroutines.launch


fun NavGraphBuilder.borrowedGraph(navHostController: NavHostController) {
    composable(BorrowedScreen.Borrowed.route) {
        val bookCheckoutViewModel: BookCheckoutViewModel = hiltViewModel()
        val scope = rememberCoroutineScope()
        BookCheckoutsScreen(
            onBorrowedBookClick = { bookISBN ->
                navHostController.navigate("${BorrowedScreen.DetailsScreen.route}/$bookISBN")
            },
            onSearchClick = {
                navHostController.navigate(BorrowedScreen.SearchBorrowed.route)
            },
            onReturnClick = {

            },
            onGetByStatusClicked = { returnStatus ->
                scope.launch {
                    when (returnStatus) {
                        "PAST" -> {
                            bookCheckoutViewModel.getAllPastBookCheckouts()
                        }

                        "ACTIVE" -> {
                            bookCheckoutViewModel.getAllActiveBookCheckouts()
                        }
                    }
                }
            }
        )
    }
    composable(
        route = "${BorrowedScreen.DetailsScreen.route}/{bookISBN}",
        arguments = listOf(navArgument("bookISBN") {
            type = NavType.StringType
        })
    ) { backStackEntry ->
        val bookViewModel: BookViewModel = hiltViewModel()
        val bookItemViewModel: BookItemViewModel = hiltViewModel()
        val bookCheckoutViewModel: BookCheckoutViewModel = hiltViewModel()
        val scope = rememberCoroutineScope()
        val bookISBN =
            backStackEntry.arguments?.getString("bookISBN")
        val bookDetails = bookViewModel.bookDetails.collectAsState().value
        val bookItems = bookItemViewModel.bookItemsResponse.collectAsState().value
        val bookCheckoutResponse =
            bookCheckoutViewModel.bookReturnResponse.collectAsState().value

        LaunchedEffect(bookCheckoutResponse) {
            when (bookCheckoutResponse) {
                is Resource.Success -> {
                    navHostController.navigate(
                        "${BorrowedScreen.ReturnDialog.route}/${"Book returned successfully"}"
                    )
                }

                is Resource.Error -> {
                    val message = bookCheckoutResponse.message
                    navHostController.navigate("${BorrowedScreen.ReturnDialog.route}/$message")
                }

                is Resource.Loading -> {

                }
            }
        }
        LaunchedEffect(Unit) {
            scope.launch {
                bookISBN?.let {
                    bookViewModel.getBookByISBN(bookISBN)
                    bookItemViewModel.getBookItemsByBookIsbn(bookISBN)
                }
            }
        }
        bookDetails?.data?.let { bookItem ->
            GeneralBookDetails(
                book = bookItem,
                onBackClicked = {
                    navHostController.popBackStack()
                },
                onReturnClick = {
                    scope.launch {
                        bookItems.data?.forEach { book ->
                            val bookCheckoutRequest = BookReturnRequest(book.id.toString())
                            bookCheckoutViewModel.returnBookItem(bookCheckoutRequest)
                        }
                    }
                }
            )
        }
    }
    composable(
        route = "${BorrowedScreen.ReturnDialog.route}/{message}",
        arguments = listOf(
            navArgument("message") {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val message = backStackEntry.arguments?.getString("message")
        ReturnDialog(
            onDismissRequest = {
                navHostController.navigate(BorrowedScreen.Borrowed.route)
            },
            message = message.toString()
        )
    }
    composable(BorrowedScreen.SearchBorrowed.route) {
        val bookCheckoutViewModel: BookCheckoutViewModel = hiltViewModel()
        val scope = rememberCoroutineScope()
        BorrowedSearchScreen(
            onBackClicked = {
                navHostController.popBackStack()
            },
            onSearchClick = { query ->
                scope.launch {
                    bookCheckoutViewModel.getAllBookCheckoutsForBookTitle(query)
                }
            }
        )
    }
}

sealed class BorrowedScreen(val route: String) {
    object Borrowed : BorrowedScreen("BORROWED")
    object DetailsScreen : BorrowedScreen("DETAILS/{bookISBN}")
    object ReturnDialog : BorrowedScreen("RETURN/{message}")
    object SearchBorrowed : BorrowedScreen("SEARCH_BORROWED")
}