package com.example.booklibrary.data.book.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookDisplay(
    val isbn: String? = null,
    val title: String? = null,
    val languages: Languages? = null,
    val image: String? = null
):Parcelable
