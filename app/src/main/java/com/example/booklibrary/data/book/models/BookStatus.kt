package com.example.booklibrary.data.book.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class BookStatus(
    val REQUESTED: String? = null,
    val PENDING_PURCHASE: String? = null,
    val REJECTED: String? = null,
    val IN_STOCK: String? = null,
    val CURRENTLY_UNAVAILABLE: String? = null
) : Parcelable
