package com.example.booklibrary.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class BookStatus(
    val REQUESTED: String? = null,
    val PENDING_PURCHASE: String? = null,
    val DECLINED: String? = null,
    val PRESENT: String? = null,
    val CURRENTLY_UNAVAILABLE: String? = null
) : Parcelable
