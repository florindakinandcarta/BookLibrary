package com.example.booklibrary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.booklibrary.Camera
import com.example.booklibrary.data.book.viewModels.AuthViewModel
import com.example.booklibrary.ui.ConfirmDialog
import com.example.booklibrary.ui.ScreensNavigator
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
import com.example.booklibrary.login.ChangePasswordScreen
import com.example.booklibrary.login.ForgotPasswordScreen
import com.example.booklibrary.login.LoginScreen
import com.example.booklibrary.ui.userProfile.ProfileScreen
import com.example.booklibrary.login.RegisterScreen
import com.example.booklibrary.ui.users.UserRoleDialog
import com.example.booklibrary.ui.users.UsersScreen

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    screensNavigator: ScreensNavigator,
    sharedViewModel: SharedViewModel = viewModel()
) {
    val parentNavController = rememberNavController()
    screensNavigator.setParentNavController(parentNavController)
    NavHost(
        navController = parentNavController,
        startDestination = BottomTab.Dashboard.route,
        modifier = modifier
    ) {
        composable(BottomTab.Dashboard.route) {
            val mainNestedNavController = rememberNavController()
            screensNavigator.setNestedNavController(mainNestedNavController)
            NavHost(
                navController = mainNestedNavController,
                startDestination = Route.Dashboard.routeName
            ) {
                composable(route = Route.Dashboard.routeName) {
                    HomeScreen(
                        officeName = "Prishtina",
                        onClickedBook = { bookISBN ->
                            screensNavigator.toRoute(Route.BookDetails(bookISBN))
                        },
                        onNotificationClick = {

                        },
                        onSearchClick = {
                            screensNavigator.toRoute(Route.SearchScreen)
                        }
                    )
                }
                composable(route = Route.BookDetails().routeName) {
                    val bookISBN = remember {
                        (screensNavigator.currentRoute.value as Route.BookDetails).bookISBN
                    }
                    BookDetails(
                        bookISBN = bookISBN,
                        onBackClicked = {
                            screensNavigator.navigateBack()
                        },
                        onAddReviewClicked = {
                            screensNavigator.toRoute(Route.ReviewDialog)
                        }
                    )
                }

                composable(route = Route.SearchScreen.routeName) {
                    SearchScreen(
                        officeName = "Prishitna",
                        onScanClick = { /*TODO AFTER IMPLEMENTING THE CAMERA*/ },
                        onBackClicked = {
                            screensNavigator.navigateBack()
                        },
                        onClickedBook = { bookISBN ->
                            screensNavigator.toRoute(Route.BookDetails(bookISBN))
                        })
                }
            }

        }
        composable(BottomTab.Requested.route) {
            RequestedScreen(
                onAddNewBook = {
                    navController.navigate(Route.SearchWithGoogle.routeName)
                },
                onClickedBook = { book ->
                    sharedViewModel.selectedBook = book
                    navController.navigate(Route.RequestedBookDetails.routeName)
                }
            )
        }

        composable(Route.BookDetails.routeName) {
            sharedViewModel.selectedBook?.let { book ->
                BookDetails(
                    book = book,
                    onBackClicked = {
                        navController.popBackStack()
                    },
                    onAddReviewClicked = {
                        navController.navigate(Route.ReviewDialog.routeName)
                    }
                )
            }
        }
        composable(Route.Reviews.routeName) {
            UserReviewSection(
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
        composable(BottomTab.Borrowed.route) {
            BorrowedBooksScreen(
                onBorrowedBookClick = { book ->
                    sharedViewModel.selectedBook = book
                    navController.navigate(Route.BorrowedBookDetails.routeName)
                },
                onReturnClick = {
                    navController.navigate(Route.ReturnDialog.routeName)
                },
                onSearchClick = {
                    navController.navigate(Route.SearchScreen.routeName)
                }
            )
        }
        composable(Route.BorrowedBookDetails.routeName) {
            sharedViewModel.selectedBook?.let { book ->
                BorrowedBookDetail(book = book,
                    onBackClicked = {
                        navController.popBackStack()
                    },
                    onEditReviewClicked = {
                        navController.navigate(Route.ReviewDialog.routeName)
                    },
                    onReportAsLostClicked = {
                        navController.navigate(Route.ConfirmDialog.routeName)
                    }
                )
            }
        }

        composable(Route.ReviewDialog.routeName) {
            UserReviewDialog(
                onDismissDialog = { navController.popBackStack() },
                onSubmitClick = {
                    navController.popBackStack() // this is temporary needs to change
                }
            )
        }
        composable(BottomTab.Profile.route) {
            ProfileScreen(
                onChangePassword = {
                    navController.navigate(Route.ChangePassword.routeName)
                },
                onLogOutClick = {
                    navController.navigate(Route.Login.routeName)
                },
                onAllUsersClicked = {
                    navController.navigate(Route.AllUsers.routeName)
                }
            )
        }
        composable(Route.AllUsers.routeName) {
            UsersScreen(
                onDeleteUser = {},
                onChangeRole = { user ->
                    sharedViewModel.user = user
                    navController.navigate(Route.UserRoleDialog.routeName)
                },
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
        composable(Route.UserRoleDialog.routeName) {
            val users = sharedViewModel.user
            UserRoleDialog(
                user = users,
                onSubmit = { userRole ->
                    users.role = userRole
                    navController.popBackStack()
                }
            )
        }

        composable(Route.ForgotPassword.routeName) {
            ForgotPasswordScreen(
                onSendEmailClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Route.Register.routeName) {
            RegisterScreen(
                onLoginClick = {
                    navController.navigate(Route.Login.routeName)
                }
            )
        }

        composable(Route.SearchWithGoogle.routeName) {
            SearchWithGoogleBookScreen(
                onBackClicked = {
                    navController.popBackStack()
                },
                onBookClicked = { googleBook ->
                    sharedViewModel.googleBook = googleBook
                    navController.navigate(Route.GoogleBookDetails.routeName)
                })
        }
        composable(Route.GoogleBookDetails.routeName) {
            sharedViewModel.googleBook?.let { googleBook ->
                GoogleBookDetails(
                    book = googleBook,
                    onBackClicked = { navController.popBackStack() }) {
                }
            }
        }
        composable(Route.Camera.routeName) {
            Camera()
        }
        composable(Route.SearchScreen.routeName) {
            SearchScreen(
                onScanClick = {
                    navController.navigate(Route.Camera.routeName)
                },
                onBackClicked = {
                    navController.popBackStack()
                },

                onClickedBook = { book ->
                    sharedViewModel.selectedBook = book
                    navController.navigate(Route.BookDetails.routeName)
                }
            )
        }
        composable(Route.ReturnDialog.routeName) {
            ReturnDialog(
                onNext = { selectedOption ->
                    when (selectedOption) {
                        "Return" -> navController.navigate(Route.Camera.routeName)
                        "Report Damage" -> {}
                        "Report as Lost" -> {}
                    }
                })
        }
        composable(Route.RequestedBookDetails.routeName) {
            sharedViewModel.selectedBook?.let { book ->
                RequestedBookDetails(
                    book = book,
                    onBackClicked = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(Route.ChangePassword.routeName) {
            ChangePasswordScreen(
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
        composable(Route.ConfirmDialog.routeName) {
            ConfirmDialog(
                onDismissRequest = {
                    navController.popBackStack()
                }
            )
        }
    }
}