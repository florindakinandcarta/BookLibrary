package com.example.booklibrary.data.models.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserUpdateDataRequest(
    val fullName: String? = null,
    val image: String? = null
) : Parcelable
