package com.example.booklibrary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.booklibrary.Camera
import com.example.booklibrary.ui.bookDetails.BookDetails
import com.example.booklibrary.ui.borrowedHistory.BorrowedBookDetail
import com.example.booklibrary.ui.borrowedHistory.BorrowedBooksScreen
import com.example.booklibrary.ui.generalSearchScreen.SearchScreen
import com.example.booklibrary.ui.home.HomeScreen
import com.example.booklibrary.ui.requested.RequestedBookDetails
import com.example.booklibrary.ui.requested.RequestedScreen
import com.example.booklibrary.ui.returnDialog.ReturnDialog
import com.example.booklibrary.ui.review.UserReviewDialog
import com.example.booklibrary.ui.review.UserReviewSection
import com.example.booklibrary.ui.searchNewBook.GoogleBookDetails
import com.example.booklibrary.ui.searchNewBook.SearchWithGoogleBookScreen
import com.example.booklibrary.ui.userProfile.ForgotPasswordScreen
import com.example.booklibrary.ui.userProfile.LoginScreen
import com.example.booklibrary.ui.userProfile.ProfileScreen
import com.example.booklibrary.ui.userProfile.RegisterScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel = viewModel()
) {
    NavHost(navController, startDestination = BottomNavItem.Dashboard.route, modifier = modifier) {
        composable(Navigation.Login.route) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(BottomNavItem.Dashboard.route)
                },
                onRegisterClick = {
                    navController.navigate(Navigation.Register.route)
                },
                onForgotPasswordClick = {
                    navController.navigate(Navigation.ForgotPassword.route)
                }
            )
        }
        composable(BottomNavItem.Dashboard.route) {
            HomeScreen(
                onClickedBook = { book ->
                    sharedViewModel.selectedBook = book
                    navController.navigate(Navigation.BookDetails.route)
                },
                onNotificationClick = {

                },
                onSearchClick = {
                    navController.navigate(Navigation.SearchScreen.route)
                }
            )
        }
        composable(BottomNavItem.Requested.route) {
            RequestedScreen(
                onAddNewBook = {
                    navController.navigate(Navigation.SearchWithGoogle.route)
                },
                onClickedBook = { book ->
                    sharedViewModel.selectedBook = book
                    navController.navigate(Navigation.RequestedBookDetails.route)
                }
            )
        }

        composable(Navigation.BookDetails.route) {
            sharedViewModel.selectedBook?.let { book ->
                BookDetails(
                    book = book,
                    onBackClicked = {
                        navController.popBackStack()
                    },
                    onAddReviewClicked = {
                        navController.navigate(Navigation.ReviewDialog.route)
                    }
                )
            }
        }
        composable(Navigation.Reviews.route) {
            UserReviewSection(
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
        composable(BottomNavItem.Borrowed.route) {
            BorrowedBooksScreen(
                onBorrowedBookClick = { book ->
                    sharedViewModel.selectedBook = book
                    navController.navigate(Navigation.BorrowedBookDetails.route)
                },
                onReturnClick = {
                    navController.navigate(Navigation.ReturnDialog.route)
                },
                onSearchClick = {
                    navController.navigate(Navigation.SearchScreen.route)
                }
            )
        }
        composable(Navigation.BorrowedBookDetails.route) {
            sharedViewModel.selectedBook?.let { book ->
                BorrowedBookDetail(book = book,
                    onBackClicked = {
                        navController.popBackStack()
                    },
                    onEditReviewClicked = {
                        navController.navigate(Navigation.ReviewDialog.route)
                    }
                )
            }
        }

        composable(Navigation.ReviewDialog.route) {
            UserReviewDialog(
                onDismissDialog = { navController.popBackStack() },
                onSubmitClick = {
                    navController.popBackStack() // this is temporary needs to change
                }
            )
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreen()
        }

        composable(Navigation.ForgotPassword.route) {
            ForgotPasswordScreen(
                onSendEmailClick = {}
            )
        }

        composable(Navigation.Register.route) {
            RegisterScreen(
                onLoginClick = {
                    navController.navigate(Navigation.Login.route)
                },
                onSignUpClick = {}
            )
        }

        composable(Navigation.SearchWithGoogle.route) {
            SearchWithGoogleBookScreen(
                onBackClicked = {
                    navController.popBackStack()
                },
                onBookClicked = { googleBook ->
                    sharedViewModel.googleBook = googleBook
                    navController.navigate(Navigation.GoogleBookDetails.route)
                })
        }
        composable(Navigation.GoogleBookDetails.route) {
            sharedViewModel.googleBook?.let { googleBook ->
                GoogleBookDetails(
                    book = googleBook,
                    onBackClicked = { navController.popBackStack() }) {
                }
            }
        }
        composable(Navigation.Camera.route) {
            Camera()
        }
        composable(Navigation.SearchScreen.route) {
            SearchScreen(
                onScanClick = {
                    navController.navigate(Navigation.Camera.route)
                },
                onBackClicked = {
                    navController.popBackStack()
                },

                onClickedBook = { book ->
                    sharedViewModel.selectedBook = book
                    navController.navigate(Navigation.BookDetails.route)
                }
            )
        }
        composable(Navigation.ReturnDialog.route) {
            ReturnDialog(
                onNext = { selectedOption ->
                    when (selectedOption) {
                        "Return" -> navController.navigate(Navigation.Camera.route)
                        "Report Damage" -> {}
                        "Report as Lost" -> {}
                    }
                })
        }
        composable(Navigation.RequestedBookDetails.route) {
            sharedViewModel.selectedBook?.let { book ->
                RequestedBookDetails(
                    book = book,
                    onBackClicked = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}