package com.example.booklibrary.data.book.models.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserUpdateRoleRequest(
    val userId: String,
    val role: String
) : Parcelable
