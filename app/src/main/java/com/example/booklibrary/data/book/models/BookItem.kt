package com.example.booklibrary.data.book.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookItem(
    val isbn: String? = null,
    val id: String? = null,
) : Parcelable
