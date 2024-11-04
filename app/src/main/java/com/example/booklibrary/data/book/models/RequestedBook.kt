package com.example.booklibrary.data.book.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.util.UUID

@Parcelize
data class RequestedBook(
    val id: String,
    val bookISBN: String? = null,
    val officeName: String? = null,
    val requestedDate: String? = null,
    val likeCounter: Int? = null,
    val bookStatus: String? = null,
    val title: String? = null,
    val image: String? = null
) : Parcelable
