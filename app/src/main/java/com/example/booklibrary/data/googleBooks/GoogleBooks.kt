package com.example.booklibrary.data.googleBooks
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoogleBooks(
    val kind: String? = null,
    val totalItems: Int? = null,
    val items: ArrayList<Items> = arrayListOf()
) : Parcelable