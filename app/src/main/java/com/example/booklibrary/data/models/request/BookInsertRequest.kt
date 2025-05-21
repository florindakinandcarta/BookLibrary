package com.example.booklibrary.data.models.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookInsertRequest(
    val isbn: String,
    val title: String,
    val description: String,
    val languages: String,
    val genres: String,
    val totalPages: Int,
    val bookStatus: String,
    val image: String,
    val ratingFromWeb: Double,
    val author: String,
) : Parcelable
