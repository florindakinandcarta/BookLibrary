package com.example.booklibrary.data.book.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class UserRole : Parcelable {
    USER,
    ADMIN,
}

val UserRole.displayName: String
    get() = when (this) {
        UserRole.USER -> "User"
        UserRole.ADMIN -> "Admin"
    }
