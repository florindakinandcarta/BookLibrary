package com.example.booklibrary.data.book.models.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.util.UUID

@Parcelize
data class BookCheckoutReturnReminderResponse(
    val userId: String? = null,
    val bookTitle: String? = null,
    val scheduledReturnDate: String? = null
) : Parcelable
