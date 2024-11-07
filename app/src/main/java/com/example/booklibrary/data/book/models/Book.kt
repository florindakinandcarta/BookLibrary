package com.example.booklibrary.data.book.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val isbn: String,
    val title: String,
    val description: String,
    val language: String,
    val genres: ArrayList<String> = arrayListOf(),
    val totalPages: Int,
    val bookStatus: String,
    val image: String,
    val ratingFromWeb: Double? = null,
    val ratingFromFirm: Double? = null,
    val authorDTOs: ArrayList<AuthorDTOs> = arrayListOf(),
    val officeName: String,
    var responseDTOs: ArrayList<String> = arrayListOf()
) : Parcelable
