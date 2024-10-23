package com.example.booklibrary.data.book.models.response

import android.os.Parcelable
import com.example.booklibrary.data.book.models.UserRole
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class UserWithRoleResponse(
    val userId: UUID,
    val fullName: String? = null,
    val email: String? = null,
    val role: UserRole? = null
) : Parcelable
