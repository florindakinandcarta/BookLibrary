package com.example.booklibrary.data.book.models.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserLoginRequest(
    val userEmail: String,
    val userPassword: String
) : Parcelable
