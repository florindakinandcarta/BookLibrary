package com.example.booklibrary.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookID(
    val isbn: String
) : Parcelable
