package com.example.booklibrary.util

fun String.toHttpsUrl(): String {
    return if (this.startsWith("http://")) {
        "https://" + this.substring(7)
    } else {
        this
    }
}
