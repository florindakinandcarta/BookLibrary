package com.example.booklibrary.data.googleBooks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageLinks(
    val smallThumbnail: String? = null,
    val thumbnail: String? = null
) : Parcelable