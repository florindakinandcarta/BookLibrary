package com.example.booklibrary.navigation

enum class Screen {
    SPLASH,
    HOME,
    REQUESTED,
    BORROWED,
    PROFILE,
    LOGIN,
    REGISTER,
    BOOKDETAILS,
    REVIEWS,
    FORGOTPASSWORD,
    REVIEWDIALOG,
    SEARCH,
    CAMERA,
    RETURN,
    CHANGEPASSWORD,
    ALLUSERS,
    USERROLEDIALOG,
    CONFIRMDIALOG,
    REQUESTEDBOOKDETAILS,
}

sealed class NavigationItem(val route: String) {
    object Splash : NavigationItem(Screen.SPLASH.name)
    object Home : NavigationItem(Screen.HOME.name)
    object Search : NavigationItem(Screen.SEARCH.name)
    object Requested : NavigationItem(Screen.REQUESTED.name)
    object Borrowed : NavigationItem(Screen.BORROWED.name)
    object Profile: NavigationItem(Screen.PROFILE.name)
    object Login: NavigationItem(Screen.LOGIN.name)
    object Register: NavigationItem(Screen.REGISTER.name)
    object BookDetails: NavigationItem(Screen.BOOKDETAILS.name)
    object Reviews: NavigationItem(Screen.REVIEWS.name)
    object ForgotPassword: NavigationItem(Screen.FORGOTPASSWORD.name)
    object ReviewDialog: NavigationItem(Screen.REVIEWDIALOG.name)
    object Camera: NavigationItem(Screen.CAMERA.name)
    object Return: NavigationItem(Screen.RETURN.name)
    object ChangePassword: NavigationItem(Screen.CHANGEPASSWORD.name)
    object AllUsers: NavigationItem(Screen.ALLUSERS.name)
    object UserRoleDialog: NavigationItem(Screen.USERROLEDIALOG.name)
    object ConfirmDialog: NavigationItem(Screen.CONFIRMDIALOG.name)
    object RequestedBookDetails: NavigationItem(Screen.REQUESTEDBOOKDETAILS.name)
}