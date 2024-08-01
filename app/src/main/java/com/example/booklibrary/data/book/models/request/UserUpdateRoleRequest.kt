package com.example.booklibrary.data.book.models.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class UserUpdateRoleRequest(
    val userId: UUID,
    val role: String
) : Parcelable
