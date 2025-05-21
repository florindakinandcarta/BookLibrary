package com.example.booklibrary.data.models.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookReturnRequest(
    val bookItemId: String
) : Parcelable
