package com.example.booklibrary.data.book.models.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class BookCheckoutRequest(
    val userId: String? = null,
    val bookItemId: String? = null
):Parcelable
