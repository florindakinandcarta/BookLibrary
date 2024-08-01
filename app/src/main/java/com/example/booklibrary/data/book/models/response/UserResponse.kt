package com.example.booklibrary.data.book.models.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class UserResponse(
    val userId: UUID,
    val fullName: String? = null,
    val email: String? = null,
    val profilePicture: String? = null
) : Parcelable
