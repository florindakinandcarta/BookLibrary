package com.example.booklibrary.data.book.models.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.util.UUID

@Parcelize
data class BookCheckoutWithUserAndBookItemResponse(
    val userFullName: String? = null,
    val bookItemId: String? = null,
    val bookTitle: String? = null,
    val bookISBN: String? = null,
    val dateBorrowed: String? = null,
    val dateReturned: String? = null,
    val scheduledReturnDate: String? = null
) : Parcelable
