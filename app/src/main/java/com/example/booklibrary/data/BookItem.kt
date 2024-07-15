package com.example.booklibrary.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class BookItem(
    val id: UUID? = null,
    val isbn: String,
    val bookItemStatus: BookStatus? = null,
    val bookItemState: BookState? = null,
):Parcelable
