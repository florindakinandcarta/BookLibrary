package com.example.booklibrary.data.googleBooks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class SearchInfo(
    val textSnippet: String? = null
) : Parcelable