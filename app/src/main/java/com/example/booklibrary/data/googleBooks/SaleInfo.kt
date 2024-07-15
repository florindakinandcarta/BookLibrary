package com.example.booklibrary.data.googleBooks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SaleInfo(
    val country     : String?  = null,
    val saleability : String?  = null,
    val isEbook     : Boolean? = null
):Parcelable