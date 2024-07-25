package com.example.booklibrary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.booklibrary.Camera
import com.example.booklibrary.ui.ConfirmDialog
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
import com.example.booklibrary.ui.userProfile.ChangePasswordScreen
import com.example.booklibrary.ui.userProfile.ForgotPasswordScreen
import com.example.booklibrary.ui.userProfile.LoginScreen
import com.example.booklibrary.ui.userProfile.ProfileScreen
import com.example.booklibrary.ui.userProfile.RegisterScreen
import com.example.booklibrary.ui.users.UserRoleDialog
import com.example.booklibrary.ui.users.UsersScreen
import com.example.booklibrary.viewModels.AuthViewModel

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel = viewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val user by authViewModel.user.collectAsState()
    NavHost(
        navController,
        startDestination = if (user != null) BottomNavItem.Dashboard.route else Navigation.Login.route,
        modifier = modifier
    ) {
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
                    },
                    onReportAsLostClicked = {
                        navController.navigate(Navigation.ConfirmDialog.route)
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
            ProfileScreen(
                onChangePassword = {
                    navController.navigate(Navigation.ChangePassword.route)
                },
                onLogOutClick = {
                    navController.navigate(Navigation.Login.route)
                },
                onAllUsersClicked = {
                    navController.navigate(Navigation.AllUsers.route)
                }
            )
        }
        composable(Navigation.AllUsers.route) {
            UsersScreen(
                onDeleteUser = {},
                onChangeRole = { user ->
                    sharedViewModel.user = user
                    navController.navigate(Navigation.UserRoleDialog.route)
                },
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
        composable(Navigation.UserRoleDialog.route) {
            val users = sharedViewModel.user
                UserRoleDialog(
                    user = users,
                    onSubmit = {userRole ->
                        users.role = userRole
                        navController.popBackStack()
                    }
                )

        }

        composable(Navigation.ForgotPassword.route) {
            ForgotPasswordScreen(
                onSendEmailClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Navigation.Register.route) {
            RegisterScreen(
                onLoginClick = {
                    navController.navigate(Navigation.Login.route)
                }
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
        composable(Navigation.ChangePassword.route) {
            ChangePasswordScreen(
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
        composable(Navigation.ConfirmDialog.route) {
            ConfirmDialog(
                onDismissRequest = {
                    navController.popBackStack()
                }
            )
        }
    }
}