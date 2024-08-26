package com.example.booklibrary.navigation

sealed class Route(
    val routeName: String
) {
    data object Dashboard : Route("dashboard")

    data object Requested : Route("requested")

    data object Borrowed : Route("borrowed")

    data object Profile : Route("profile")

    data object Login : Route("login")

    data object Register : Route("register")

    data class BookDetails(
        val bookISBN: String = ""
    ) : Route("bookDetails/{bookISBN}") {
        override val navCommand: String
            get() = routeName
                .replace("{bookISBN}", bookISBN)
    }

    data object BorrowedBookDetails : Route("borrowedBookDetails")

    data object Reviews : Route("reviews")

    data object ForgotPassword : Route("forgotPassword")

    data object ReviewDialog : Route("reviewDialog")

    data object SearchWithGoogle : Route("searchWithGoogle")

    data class GoogleBookDetails(
        val bookISBN: String = ""
    ) : Route("googleBookDetails/{bookISBN}") {
        override val navCommand: String
            get() = routeName
                .replace("{bookISBN}", bookISBN)
    }

    data object Camera : Route("camera")

    data object SearchScreen : Route("search")

    data object ReturnDialog : Route("returnDialog")

    data object RequestedBookDetails : Route("requestedBookDetails")

    data object ChangePassword : Route("changePassword")

    data object ConfirmDialog : Route("confirmDialog")

    data object AllUsers : Route("allUsers")

    data object UserRoleDialog : Route("userRoleDialog")

    open val navCommand = routeName
}