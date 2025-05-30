package com.example.booklibrary.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class ReturnStatus(val displayName: String) : Parcelable {
    PAST("Past borrowed"),
    ACTIVE("Active"),
}