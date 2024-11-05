package com.example.booklibrary.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.booklibrary.data.User
import com.example.booklibrary.data.book.models.request.RequestedBookRequestDTO
import com.example.booklibrary.data.book.viewModels.RequestedBookViewModel
import com.example.booklibrary.data.book.viewModels.UserViewModel
import com.example.booklibrary.ui.ErrorDialog
import com.example.booklibrary.ui.SuccessDialog
import com.example.booklibrary.ui.generalScreens.SearchScreen
import com.example.booklibrary.ui.requested.GoogleBooksViewModel
import com.example.booklibrary.ui.requested.RequestedBookDetails
import com.example.booklibrary.ui.requested.RequestedScreen
import com.example.booklibrary.util.Resource
import com.example.booklibrary.util.saveUserJWTToken
import kotlinx.coroutines.launch

@SuppressLint("NewApi")
fun NavGraphBuilder.requestedGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.REQUESTED,
        startDestination = RequestedScreen.Requested.route
    ) {
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
//                    navHostController.navigate("${RequestedScreen.RequestedDetails.route}/$bookISBN")
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
//        composable(
//            route = "${RequestedScreen.RequestedDetails.route}/{bookISBN}",
//            arguments = listOf(
//                navArgument("bookISBN") {
//                    type = NavType.StringType
//                }
//            )
//        ) { backStackEntry ->
//            val bookData = backStackEntry.arguments?.getString("bookISBN")
//            val bookRequest = bookData?.let { RequestedBookRequestDTO(it) }
//            val googleBooksViewModel: GoogleBooksViewModel = hiltViewModel()
//            val requestedBookViewModel: RequestedBookViewModel = hiltViewModel()
//            val scope = rememberCoroutineScope()
//            val book = requestedBookViewModel.book.collectAsState().value
//            LaunchedEffect(book) {
//                when (book) {
//                    is Resource.Success -> {
//                        navHostController.navigate(RequestedScreen.SuccessDialog.route)
//                    }
//
//                    is Resource.Error -> {
//                        navHostController.navigate("${RequestedScreen.ErrorDialog.route}/${book.message}")
//                    }
//
//                    is Resource.Loading -> {
//                        // loader
//                    }
//                }
//            }
//            bookData?.let { googleBooksViewModel.fetchBookByISBN(it) }
//            val responseBooks by googleBooksViewModel.responseBooks.collectAsState()
//            responseBooks?.data?.items?.forEach { bookItem ->
//                bookItem.volumeInfo?.let { volumeInfo ->
//                    RequestedBookDetails(
//                        volumeInfo,
//                        onRequestClick = {
//                            scope.launch {
//                                bookRequest?.let { requestedBookViewModel.insertNewRequestedBook(it) }
//                            }
//                        },
//                        onBackClick = {
//                            navHostController.popBackStack()
//                        }
//                    )
//                }
//            }
//
//        }
        composable(RequestedScreen.SuccessDialog.route) {
            SuccessDialog(
                onDismissRequest = {
                    navHostController.navigate(RequestedScreen.Requested.route)
                }
            )
        }

        composable(route = "${RequestedScreen.ErrorDialog.route}/{errorMessage}",
            arguments = listOf(
                navArgument("errorMessage") {
                    type = NavType.StringType
                }
            )) { backStackEntry ->
            val errorMessage = backStackEntry.arguments?.getString("errorMessage")
            errorMessage?.let {
                ErrorDialog(
                    message = it,
                    onDismissRequest = {
                        navHostController.navigate(RequestedScreen.Requested.route)
                    }
                )
            }
        }
        composable(RequestedScreen.AddNewBook.route) {
            val requestedBookViewModel: RequestedBookViewModel = hiltViewModel()
            val scope = rememberCoroutineScope()
            val book = requestedBookViewModel.book.collectAsState().value
            LaunchedEffect(book) {
                when (book) {
                    is Resource.Success -> {
                        navHostController.navigate(RequestedScreen.SuccessDialog.route)
                    }

                    is Resource.Error -> {
                        navHostController.navigate("${RequestedScreen.ErrorDialog.route}/${book.message}")
                    }

                    is Resource.Loading -> {
                        // loader
                    }
                }
            }
            SearchScreen(
                onScanClick = {},
                onBackClicked = {
                    navHostController.popBackStack()
                },
                onClickedBook = {},
                onSearchClick = { bookISBN ->
                    val bookRequest =RequestedBookRequestDTO(bookISBN)
//                    navHostController.navigate("${RequestedScreen.RequestedDetails.route}/$bookISBN")
                    scope.launch {
                        requestedBookViewModel.insertNewRequestedBook(bookRequest)
                    }
                }
            )
        }
    }
}

sealed class RequestedScreen(val route: String) {
    object Requested : RequestedScreen("REQUESTED")
    object RequestedDetails : RequestedScreen("DETAILS/{bookISBN}")
    object AddNewBook : RequestedScreen("ADDNEWBOOK")
    object SuccessDialog : RequestedScreen("SUCCESS")
    object ErrorDialog : RequestedScreen("ERROR/{errorMessage}")
}