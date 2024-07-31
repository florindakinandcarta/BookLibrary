package com.example.booklibrary.data.book.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Languages(
    val ENGLISH: String? = null,
    val ALBANIAN: String? = null,
    val MACEDONIAN: String? = null
): Parcelable