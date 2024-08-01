package com.example.booklibrary.data.book.models.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OfficeResponse(
    val name: String? = null
) : Parcelable
