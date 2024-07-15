package com.example.booklibrary.data.googleBooks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReadingModes(
    val text: Boolean? = null,
    val image: Boolean? = null
) : Parcelable