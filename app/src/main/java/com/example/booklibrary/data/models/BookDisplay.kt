package com.example.booklibrary.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookDisplay(
    val isbn: String? = null,
    val title: String? = null,
    val language: String? = null,
    val image: String? = null
) : Parcelable
