package com.example.booklibrary.data.book.models.request

import android.os.Parcelable
import com.example.booklibrary.data.book.models.BookStatus
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class BookChangeStatus(
    val requestedBookId: String,
    val newBookStatus: String
) : Parcelable
