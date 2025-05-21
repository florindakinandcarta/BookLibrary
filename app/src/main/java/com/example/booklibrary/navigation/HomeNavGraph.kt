package com.example.booklibrary.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.booklibrary.R
import com.example.booklibrary.data.models.request.BookCheckoutRequest
import com.example.booklibrary.ui.barcode.BookISBNScanner
import com.example.booklibrary.ui.generalScreens.SearchScreen
import com.example.booklibrary.ui.generalScreens.bookDetails.BookDetails
import com.example.booklibrary.ui.home.HomeScreen
import com.example.booklibrary.util.showToast
import com.example.booklibrary.viewModels.BookCheckoutViewModel
import com.example.booklibrary.viewModels.BookItemViewModel
import com.example.booklibrary.viewModels.BookViewModel
import com.example.booklibrary.viewModels.UserViewModel
import kotlinx.coroutines.launch


fun NavGraphBuilder.homeNavGraph(navHostController: NavHostController) {
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
            onScanClick = {
                navHostController.navigate(HomeScreen.BookISBNScanner.route)
            },
            onBackClicked = {
                navHostController.popBackStack()
            },
            onClickedBook = { bookISBN ->
                navHostController.navigate("${HomeScreen.BookDetails.route}/$bookISBN")
            },
            placeholderText = "Search with title or scan",
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
        route = "${HomeScreen.BookDetails.route}/{bookISBN}",
        arguments = listOf(
            navArgument("bookISBN") {
                type = NavType.StringType
            })
    ) { backStackEntry ->
        val bookViewModel: BookViewModel = hiltViewModel()
        val userViewModel: UserViewModel = hiltViewModel()
        val bookCheckoutViewModel: BookCheckoutViewModel = hiltViewModel()
        val userID = userViewModel.userInfo.collectAsState().value
        val bookItemViewModel: BookItemViewModel = hiltViewModel()
        val scope = rememberCoroutineScope()
        val bookISBN = backStackEntry.arguments?.getString("bookISBN")
        val bookDetails = bookViewModel.bookDetails.collectAsState().value
        val bookID = bookItemViewModel.bookItemsResponse.collectAsState().value
        val borrowBookRequest = bookID.data?.firstOrNull()?.let { bookItem ->
            BookCheckoutRequest(userID.data?.userId, bookItem.id)
        }
        val context = LocalContext.current
        LaunchedEffect(Unit) {
            scope.launch {
                bookISBN?.let {
                    bookViewModel.getBookByISBN(bookISBN)
                    bookItemViewModel.getBookItemsByBookIsbn(bookISBN)
                }
            }
        }
        LaunchedEffect(bookID) {
            if (bookID.data.isNullOrEmpty()) {
                context.showToast(context.getString(R.string.book_not_available))
            }
        }
        bookDetails?.data?.let { bookItem ->
            BookDetails(
                book = bookItem,
                onBackClicked = {
                    navHostController.popBackStack()
                },
                onBorrowClick = {
                    scope.launch {
                        borrowBookRequest?.let {
                            bookCheckoutViewModel.borrowBookItem(it)
                        }
                    }
                }
            )
        }
    }
    composable(HomeScreen.BookISBNScanner.route) {
        BookISBNScanner(
            onBarcodeScannerClosed = {
                navHostController.popBackStack(HomeScreen.Search.route, false)
            },
            onSuccessfulScan = { bookISBN ->
                navHostController.navigate("${HomeScreen.BookDetails.route}/$bookISBN")
            }
        )
    }
}


sealed class HomeScreen(val route: String) {
    object Search : HomeScreen("SEARCH")
    object BookDetails : HomeScreen("HOMEDETAILS/{bookISBN}")
    object BookISBNScanner : HomeScreen("HOMESCANNER")
}