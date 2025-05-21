package com.example.booklibrary.data.models.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RequestedBookRequestDTO(
    val bookIsbn: String
) : Parcelable
