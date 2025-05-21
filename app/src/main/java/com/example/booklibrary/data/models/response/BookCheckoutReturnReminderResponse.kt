package com.example.booklibrary.data.models.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookCheckoutReturnReminderResponse(
    val userId: String? = null,
    val bookTitle: String? = null,
    val scheduledReturnDate: String? = null
) : Parcelable
