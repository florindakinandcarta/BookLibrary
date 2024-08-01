package com.example.booklibrary.data.book.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val isbn: String? = null,
    val title: String,
    val description: String,
    val language: String? = null,
    val genre: String? = null,
    val totalPages: Int? = null,
    val bookStatus: BookStatus? = null,
    val image: String? = null,
    val ratingFromWeb: Double? = null,
    val ratingFromFirm: Double? = null,
    val author: String? = null
) : Parcelable
