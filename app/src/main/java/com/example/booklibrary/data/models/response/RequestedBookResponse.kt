package com.example.booklibrary.data.models.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RequestedBookResponse(
    val id: String,
    val bookISBN: String,
    val officeName: String,
    val requestedDate: String,
    val likeCounter: Int,
    val bookStatus: String,
    val title: String,
    val image: String
) : Parcelable