package com.example.booklibrary.data.book.models.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRegistrationRequest(
    val fullName: String,
    val email: String,
    val officeName: String,
    val password: String,
) : Parcelable
