package com.example.booklibrary.data.models.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserWithRoleResponse(
    val userId: String,
    val fullName: String? = null,
    val email: String? = null,
    val role: String? = null
) : Parcelable
