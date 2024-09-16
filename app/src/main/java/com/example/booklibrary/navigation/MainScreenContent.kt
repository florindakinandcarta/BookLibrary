package com.example.booklibrary.navigation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.booklibrary.data.book.viewModels.UserViewModel
import com.example.booklibrary.login.ChangePasswordScreen
import com.example.booklibrary.ui.borrowedHistory.BorrowedBooksScreen
import com.example.booklibrary.ui.generalScreens.SearchScreen
import com.example.booklibrary.ui.generalScreens.bookDetails.BookDetails
import com.example.booklibrary.ui.home.HomeScreen
import com.example.booklibrary.ui.requested.RequestedScreen
import com.example.booklibrary.ui.returnDialog.ReturnDialog
import com.example.booklibrary.ui.search.SearchWithGoogleBookScreen
import com.example.booklibrary.ui.userProfile.ProfileScreen
import com.example.booklibrary.ui.users.UserRoleDialog
import com.example.booklibrary.ui.users.UsersScreen
import kotlinx.coroutines.launch

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    screensNavigator: ScreensNavigator,
    sharedViewModel: SharedViewModel = viewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val parentNavController = rememberNavController()
    screensNavigator.setParentNavController(parentNavController)
    val scope = rememberCoroutineScope()
    Surface {
        NavHost(
            navController = parentNavController,
            startDestination = Route.Dashboard.routeName,
            modifier = modifier
        ) {
            composable(Route.Dashboard.routeName) {
                val mainNestedNavController = rememberNavController()
                screensNavigator.setNestedNavController(mainNestedNavController)
                NavHost(
                    navController = mainNestedNavController,
                    startDestination = Route.Dashboard.routeName
                ) {
                    composable(route = Route.Dashboard.routeName) {
                        HomeScreen(
                            onClickedBook = { bookISBN ->
                                screensNavigator.toRoute(Route.BookDetails)
                            },
                            onNotificationClick = {

                            },
                            onSearchClick = {
                                screensNavigator.toRoute(Route.SearchScreen)
                            }
                        )
                    }
                    composable(route = Route.BookDetails.routeName) {
//                        val bookISBN = remember {
//                            (screensNavigator.currentRoute.value as Route.BookDetails).bookISBN
//                        }
                        BookDetails(
                            bookISBN = "qwerty",
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
                            onScanClick = { /*TODO AFTER IMPLEMENTING THE CAMERA*/ },
                            onBackClicked = {
                                screensNavigator.navigateBack()
                            },
                            onClickedBook = { bookISBN ->
//                                screensNavigator.toRoute(Route.BookDetails(bookISBN))
                            })
                    }
                }

            }


            composable(Route.Requested.routeName) {
                val requestedNavController = rememberNavController()
                screensNavigator.setNestedNavController(requestedNavController)
                NavHost(
                    navController = requestedNavController,
                    startDestination = Route.Requested.routeName
                ) {
                    composable(Route.Requested.routeName) {
                        RequestedScreen(
                            onAddNewBook = {
                                screensNavigator.toRoute(Route.SearchWithGoogle)
                            },
                            onClickedBook = { isbn ->
                                screensNavigator.toRoute(Route.BookDetails)
                            })
                    }
                    composable(Route.SearchWithGoogle.routeName) {
                        SearchWithGoogleBookScreen(onBackClicked = {
                            screensNavigator.navigateBack()
                        },
                            onBookClicked = { isbn ->
                                screensNavigator.toRoute(Route.GoogleBookDetails(isbn))
                            })
                    }
                    composable(route = Route.BookDetails.routeName) {
//                        val bookISBN = remember {
//                            (screensNavigator.currentRoute.value as Route.BookDetails).bookISBN
//                        }
                        BookDetails(
                            bookISBN = "qwerty",
                            onBackClicked = {
                                screensNavigator.navigateBack()
                            },
                            onAddReviewClicked = {
                                screensNavigator.toRoute(Route.ReviewDialog)
                            }
                        )
                    }
                }
            }
            composable(Route.Borrowed.routeName) {
                val borrowedNavController = rememberNavController()
                screensNavigator.setNestedNavController(borrowedNavController)
                NavHost(
                    navController = borrowedNavController,
                    startDestination = Route.Borrowed.routeName
                ) {
                    composable(Route.Borrowed.routeName) {
                        BorrowedBooksScreen(
                            onBorrowedBookClick = {
                                screensNavigator.toRoute(Route.BookDetails)
                            },
                            onReturnClick = {
                                screensNavigator.toRoute(Route.ReturnDialog)
                            },
                            onSearchClick = {
                                screensNavigator.toRoute(Route.SearchWithGoogle)
                            }
                        )
                    }
                    composable(Route.SearchWithGoogle.routeName) {
                        SearchWithGoogleBookScreen(onBackClicked = { /*TODO*/ }, onBookClicked = {})
                    }
                    composable(route = Route.BookDetails.routeName) {
//                        val bookISBN = remember {
//                            (screensNavigator.currentRoute.value as Route.BookDetails).bookISBN
//                        }
                        BookDetails(
                            bookISBN = "qwerty",
                            onBackClicked = {
                                screensNavigator.navigateBack()
                            },
                            onAddReviewClicked = {
                                screensNavigator.toRoute(Route.ReviewDialog)
                            }
                        )
                    }
                    composable(Route.ReturnDialog.routeName) {
                        ReturnDialog(
                            onNext = {

                            }
                        )
                    }
                }
            }

            composable(Route.Profile.routeName) {
                val profileNavController = rememberNavController()
                screensNavigator.setNestedNavController(profileNavController)
                NavHost(
                    navController = profileNavController,
                    startDestination = Route.Profile.routeName
                ) {
                    composable(Route.Profile.routeName) {
                        ProfileScreen(
                            onChangePassword = {
                                screensNavigator.toRoute(Route.ChangePassword)
                            },
                            onAllUsersClicked = {
                                screensNavigator.toRoute(Route.AllUsers)
                            },
                            onChangeProfilePhotoClicked = {

                            })
                    }
                    composable(Route.ChangePassword.routeName) {
                        ChangePasswordScreen(
                            onBackClicked = {
                                screensNavigator.navigateBack()
                            }
                        )
                    }
                    composable(Route.AllUsers.routeName) {
                        UsersScreen(
                            onDeleteUser = {},
                            onChangeRole = { user ->
                                scope.launch {
                                    userViewModel.updateUserRole(user)
                                }
                                screensNavigator.toRoute(Route.UserRoleDialog)
                            },
                            onBackClicked = {
                                screensNavigator.navigateBack()
                            })
                    }
                    composable(Route.UserRoleDialog.routeName) {
                        val users = sharedViewModel.user
                        UserRoleDialog(
                            user = users,
                            onSubmit = {
                                users.role = it
                                screensNavigator.navigateBack()
                            })
                    }
                }
            }
        }
    }
}