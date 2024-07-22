package com.example.booklibrary.navigation

sealed class Navigation(
    var route: String
) {
    object Login : Navigation(
        "login"
    )

    object Register : Navigation(
        "register"
    )

    object BookDetails : Navigation(
        "bookDetails"
    )
    object BorrowedBookDetails : Navigation(
        "borrowedBookDetails"
    )

    object Reviews : Navigation(
        "reviews"
    )

    object ForgotPassword : Navigation(
        "forgotPassword"
    )

    object ReviewDialog : Navigation(
        "reviewDialog"
    )
    object SearchWithGoogle : Navigation(
        "searchWithGoogle"
    )

    object GoogleBookDetails : Navigation(
        "googleBookDetails"
    )

    object Camera : Navigation(
        "camera"
    )

    object SearchScreen : Navigation(
        "search"
    )
    object ReturnDialog: Navigation(
        "returnDialog"
    )
    object RequestedBookDetails: Navigation(
        "requestedBookDetails"
    )
    object ChangePassword: Navigation(
        "changePassword"
    )
    object ConfirmDialog: Navigation(
        "confirmDialog"
    )
    object AllUsers: Navigation(
        "allUsers"
    )
}