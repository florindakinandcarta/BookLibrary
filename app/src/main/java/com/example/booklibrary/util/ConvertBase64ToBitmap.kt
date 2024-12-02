package com.example.booklibrary.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream


fun convertBase64ToBitmap(profilePicture: String?): Bitmap? {
    return profilePicture?.let {
        val decodedBytes = Base64.decode(it, it.length)
        BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }
}

fun convertBitmapToString(pictureTaken: Bitmap): String {
    val outputStream = ByteArrayOutputStream()
    pictureTaken.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    val byteArray = outputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.NO_WRAP)

}