package com.example.booklibrary.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.booklibrary.data.book.models.BookID
import com.example.booklibrary.data.book.models.request.RequestedBookRequestDTO
import com.example.booklibrary.data.book.viewModels.BookItemViewModel
import com.example.booklibrary.data.book.viewModels.BookViewModel
import com.example.booklibrary.data.book.viewModels.RequestedBookViewModel
import com.example.booklibrary.ui.BookRequestedSuccessDialog
import com.example.booklibrary.ui.barcode.BookISBNScanner
import com.example.booklibrary.ui.generalScreens.SearchScreen
import com.example.booklibrary.ui.requested.RequestedBookDetails
import com.example.booklibrary.ui.requested.RequestedScreen
import kotlinx.coroutines.launch

@SuppressLint("NewApi")
fun NavGraphBuilder.requestedGraph(navHostController: NavHostController) {
    composable(RequestedScreen.Requested.route) {
        val requestedBookViewModel: RequestedBookViewModel = hiltViewModel()
        val scope = rememberCoroutineScope()
        LaunchedEffect(Unit) {
            scope.launch {
                requestedBookViewModel.getAllRequestedBooks()
            }
        }
        RequestedScreen(
            onAddNewBook = {
                navHostController.navigate(RequestedScreen.AddNewBook.route)
            },
            onClickedBook = { bookISBN ->
                navHostController.navigate("${RequestedScreen.RequestedDetails.route}/$bookISBN")
            },
            onLikeBook = { bookISBN ->
                val bookLikeRequest = RequestedBookRequestDTO(bookISBN)
                scope.launch {
                    requestedBookViewModel.handleRequestedBookLike(bookLikeRequest)
                }
            },
            onGetBookByStatusClicked = { status ->
                scope.launch {
                    requestedBookViewModel.getRequestedBooksByBookStatus(status)
                }
            },
            onChangeStatusClicked = { bookChangeStatus ->
                scope.launch {
                    requestedBookViewModel.changeBookStatus(bookChangeStatus)
                }
            }
        )
    }
    composable(
        route = "${RequestedScreen.RequestedDetails.route}/{bookISBN}",
        arguments = listOf(
            navArgument("bookISBN") {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val book = backStackEntry.arguments?.getString("bookISBN")
        println("isbn: $book")
        val bookViewModel: BookViewModel = hiltViewModel()
        val bookItemViewModel: BookItemViewModel = hiltViewModel()
        val scope = rememberCoroutineScope()
        val bookDetails = bookViewModel.bookDetails.collectAsState().value
        LaunchedEffect(Unit) {
            scope.launch {
                book?.let {
                    bookViewModel.getBookByISBN(book)
                }
            }
        }
        bookDetails?.data?.let { bookItem ->
            RequestedBookDetails(
                book = bookItem,
                onBackClick = {
                    navHostController.popBackStack()
                },
                onCreateItemClick = { bookISBN ->
                    scope.launch {
                        val bookItemRequest = BookID(bookISBN)
                        bookItemViewModel.createBookItem(bookItemRequest)
                    }
                }
            )
        }
    }
    composable(
        route = "${RequestedScreen.SuccessDialog.route}/{bookISBN}",
        arguments = listOf(navArgument("bookISBN") {
            type = NavType.StringType
        })
    ) { backStackEntry ->
        val requestedBookViewModel: RequestedBookViewModel = hiltViewModel()
        val scope = rememberCoroutineScope()
        val bookISBN = backStackEntry.arguments?.getString("bookISBN")
        LaunchedEffect(Unit) {
            scope.launch {
                val bookRequest = bookISBN?.let { RequestedBookRequestDTO(it) }
                bookRequest?.let { requestedBookViewModel.insertNewRequestedBook(it) }
            }
        }
        BookRequestedSuccessDialog(
            onDismissRequest = {
                navHostController.navigate(RequestedScreen.Requested.route)
            }
        )
    }
    composable(RequestedScreen.AddNewBook.route) {
        SearchScreen(
            onScanClick = {
                navHostController.navigate(RequestedScreen.BookISBNScanner.route)
            },
            onBackClicked = {
                navHostController.popBackStack()
            },
            placeholderText = "Insert ISBN or scan",
            onClickedBook = {},
            onSearchClick = { bookISBN ->
                navHostController.navigate("${RequestedScreen.SuccessDialog.route}/$bookISBN")
            }
        )
    }
    composable(route = RequestedScreen.BookISBNScanner.route) {
        BookISBNScanner(
            onBarcodeScannerClosed = {
                navHostController.popBackStack(RequestedScreen.AddNewBook.route, false)
            },
            onSuccessfulScan = { bookISBN ->
                navHostController.navigate("${RequestedScreen.SuccessDialog.route}/$bookISBN")
            }
        )
    }
}

sealed class RequestedScreen(val route: String) {
    object Requested : RequestedScreen("REQUESTED")
    object RequestedDetails : RequestedScreen("REQUESTEDDETAILS/{bookISBN}")
    object AddNewBook : RequestedScreen("ADDNEWBOOK")
    object SuccessDialog : RequestedScreen("SUCCESS/{bookISBN}")
    object BookISBNScanner : RequestedScreen("REQUESTEDSCANNER")
}