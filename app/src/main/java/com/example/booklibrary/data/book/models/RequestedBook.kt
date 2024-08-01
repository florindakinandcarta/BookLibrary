package com.example.booklibrary.data.book.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.util.UUID

@Parcelize
data class RequestedBook(
    val id: UUID? = null,
    val requestedDate: LocalDate? = null,
    val likeCounter: Long? = null,
    val bookISBN: String? = null,
    val bookStatus: BookStatus? = null,
    val title: String? = null,
    val image: String? = null
) : Parcelable
