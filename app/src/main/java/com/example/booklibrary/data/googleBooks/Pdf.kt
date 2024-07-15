package com.example.booklibrary.data.googleBooks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pdf(
    val isAvailable: Boolean? = null,
    val downloadLink: String? = null,
    val acsTokenLink: String? = null
) : Parcelable