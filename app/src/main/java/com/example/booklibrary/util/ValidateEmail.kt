package com.example.booklibrary.util

fun validateEmail(email: String): Boolean {
    val isEmailValid: Boolean =
        !(Regex("[a-zA-Z.]+@kinandcarta.com").matches(email) ||
                Regex("[a-zA-Z.]+@valtech.com").matches(email))
    return isEmailValid
}