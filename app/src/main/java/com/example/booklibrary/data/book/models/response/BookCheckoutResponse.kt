package com.example.booklibrary.data.book.models.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class BookCheckoutResponse(
    val bookTitle: String,
    val bookISBN: String,
    val dateBorrowed: LocalDate,
    val dateReturned: LocalDate,
    val scheduledReturn: LocalDate
) : Parcelable
