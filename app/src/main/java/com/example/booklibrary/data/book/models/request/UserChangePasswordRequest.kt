package com.example.booklibrary.data.book.models.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserChangePasswordRequest(
    val userId: String,
    val oldPassword: String? = null,
    val newPassword: String? = null
) : Parcelable
