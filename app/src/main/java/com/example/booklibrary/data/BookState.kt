package com.example.booklibrary.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class BookState(
    val AVAILABLE: String? = null,
    val BORROWED: String? = null,
    val OVERDUE: String? = null,
    val LOST: String? = null,
    val DAMAGED: String? = null
): Parcelable