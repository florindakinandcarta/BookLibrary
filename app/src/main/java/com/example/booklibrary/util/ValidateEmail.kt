package com.example.booklibrary.util

fun validateEmail(email: String): Boolean {
    val isEmailValid: Boolean =
        !(Regex("[a-zA-Z0-9.]+@kinandcarta.com").matches(email) ||
                Regex("[a-zA-Z0-9.]+@valtech.com").matches(email))
    return isEmailValid
}