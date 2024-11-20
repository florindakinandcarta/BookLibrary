package com.example.booklibrary.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.Toast

fun String.toHttpsUrl(): String {
    return if (this.startsWith("http://")) {
        "https://" + this.substring(7)
    } else {
        this
    }
}

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, message, duration).show()
}