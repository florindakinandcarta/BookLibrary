package com.example.booklibrary.data.book.models.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class UserUpdateDataRequest(
    val userID: UUID,
    val fullName: String? = null,
    val profilePicture: String? = null
) : Parcelable
