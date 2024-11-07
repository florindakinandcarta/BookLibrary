package com.example.booklibrary.data.book.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthorDTOs(
    val fullName: String
) : Parcelable
