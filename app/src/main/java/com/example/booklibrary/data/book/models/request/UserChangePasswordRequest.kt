package com.example.booklibrary.data.book.models.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class UserChangePasswordRequest(
    val userId: UUID,
    val oldPassword: String,
    val newPassword: String
) : Parcelable
