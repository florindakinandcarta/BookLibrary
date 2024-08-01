package com.example.booklibrary.data.book.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class BookItem(
    val isbn: String? = null,
    val id: UUID? = null,
) : Parcelable
