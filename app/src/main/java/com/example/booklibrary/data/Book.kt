package com.example.booklibrary.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val isbn: String? = null,
    val title: String? = null,
    val language: String? = null,
    val image: String? = null,
    val author: String? = null,
    val genre: String? = null,
    val numberOfLikes: Int? = null
) : Parcelable
