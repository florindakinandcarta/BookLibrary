package com.example.booklibrary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.room.util.copy
import com.example.booklibrary.data.User
import com.example.booklibrary.data.book.models.request.BookCheckoutRequest
import com.example.booklibrary.data.book.viewModels.BookCheckoutViewModel
import com.example.booklibrary.data.book.viewModels.BookItemViewModel
import com.example.booklibrary.data.book.viewModels.BookViewModel
import com.example.booklibrary.data.book.viewModels.UserViewModel
import com.example.booklibrary.ui.generalScreens.SearchScreen
import com.example.booklibrary.ui.generalScreens.bookDetails.BookDetails
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
            val bookCheckoutViewModel: BookCheckoutViewModel = hiltViewModel()
            val userViewModel: UserViewModel = hiltViewModel()
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                scope.launch {
                    userViewModel.getUserInfo()
                    bookViewModel.getAllBooks()
                    bookCheckoutViewModel.getAllBookCheckoutsForUser()
                }
            }
            HomeScreen(
                onSearchClick = {
                    navHostController.navigate(HomeScreen.Search.route)
                },
                onClickedBook = { bookISBN ->
                    navHostController.navigate("${HomeScreen.BookDetails.route}/$bookISBN")
                },
                onNotificationClick = {},
                onSelectedLanguageClick = { language ->
                    scope.launch {
                        bookViewModel.getBooksByLanguage(language)
                    }
                },
                onGetBookByGenreClicked = { genre ->
                    if (genre == "Available") {
                        scope.launch {
                            bookViewModel.getAvailableBooks()
                        }
                    } else {
                        scope.launch {
                            bookViewModel.getBooksByGenre(genre)
                        }
                    }
                }
            )
        }

        composable(route = HomeScreen.Search.route) {
            val bookViewModel: BookViewModel = hiltViewModel()
            val scope = rememberCoroutineScope()
            SearchScreen(
                onScanClick = {},
                onBackClicked = {
                    navHostController.popBackStack()
                },
                onClickedBook = { bookISBN ->
                    navHostController.navigate("${HomeScreen.BookDetails.route}/$bookISBN")
                },
                onSearchClick = { bookTitle ->
                    val correctedBookTitle = bookTitle.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase() else it.toString()
                    }
                    scope.launch {
                        bookViewModel.getBooksByTitle(correctedBookTitle)
                    }
                }
            )
        }

        composable(
            route = "${HomeScreen.BookDetails.route}/{book}",
            arguments = listOf(
                navArgument("book") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val bookViewModel: BookViewModel = hiltViewModel()
            val userViewModel: UserViewModel = hiltViewModel()
            val bookCheckoutViewModel: BookCheckoutViewModel = hiltViewModel()
            val userID = userViewModel.userInfo.collectAsState().value
            val bookItemViewModel: BookItemViewModel = hiltViewModel()
            val scope = rememberCoroutineScope()
            val book = backStackEntry.arguments?.getString("book")
            val bookDetails = bookViewModel.bookDetails.collectAsState().value
            val bookID = bookItemViewModel.bookItemsResponse.collectAsState().value
            val borrowBookRequest = bookID.data?.firstOrNull()?.let { bookItem ->
                BookCheckoutRequest(userID.data?.userId, bookItem.id)
            }
            LaunchedEffect(Unit) {
                scope.launch {
                    book?.let {
                        bookViewModel.getBookByISBN(book)
                        bookItemViewModel.getBookItemsByBookIsbn(book)
                    }
                }
            }
            bookDetails?.data?.let { bookItem ->
                BookDetails(
                    book = bookItem,
                    onBackClicked = {
                        navHostController.popBackStack()
                    },
                    onAddReviewClicked = {

                    },
                    onBorrowClick = {
                        scope.launch {
                            borrowBookRequest?.let {
                                bookCheckoutViewModel.borrowBookItem(it)
                            }
                        }
                    })
            }
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


sealed class HomeScreen(val route: String) {
    object Search : HomeScreen("SEARCH")
    object BookDetails : HomeScreen("DETAILS/{book}")
}