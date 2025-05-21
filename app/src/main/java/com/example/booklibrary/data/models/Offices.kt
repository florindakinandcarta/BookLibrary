package com.example.booklibrary.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Offices(val displayName: String) : Parcelable {
    SKOPJE("Skopje"),
    PRISTINA("Pristina"),
    SOFIA("Sofia"),
    VELIKO("Veliko Tarnovo"),
    EDINBURGH("Edinburgh"),
    BRISTOL("Bristol"),
    WROCLAW("Wrocław"),
    BOGOTA("Bogotá"),
    MANCHESTER("Manchester"),
    LIVERPOOL("Liverpool"),
    BUENOS_AIRES("Buenos Aires"),
    PORTLAND("Portland"),
    DENVER("Denver"),
    NEW_YORK("New York");

    override fun toString(): String {
        return displayName
    }
}
