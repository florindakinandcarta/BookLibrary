package com.example.booklibrary.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genres(
    val genreTitle: String? = null
) : Parcelable
