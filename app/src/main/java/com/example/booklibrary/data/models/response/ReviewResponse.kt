package com.example.booklibrary.data.models.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewResponse(
    val bookISBN: String? = null,
    val userEmail: String? = null,
    val message: String? = null,
    val rating: Int? = null
) : Parcelable
