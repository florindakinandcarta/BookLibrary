package com.example.booklibrary.data.models.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookCheckoutRequest(
    val userId: String? = null,
    val bookItemId: String? = null
) : Parcelable
