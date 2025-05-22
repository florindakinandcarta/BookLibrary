package com.example.booklibrary.data.models.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookCheckoutResponse(
    val bookTitle: String,
    val bookISBN: String,
    val dateBorrowed: String,
    val dateReturned: String,
    val scheduledReturnDate: String
) : Parcelable