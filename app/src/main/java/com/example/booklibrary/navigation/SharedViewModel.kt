package com.example.booklibrary.navigation

import androidx.lifecycle.ViewModel
import com.example.booklibrary.data.Book
import com.example.booklibrary.data.User
import com.example.booklibrary.data.googleBooks.Items

class SharedViewModel : ViewModel() {
    var selectedBook: Book? = null
    var googleBook: Items? = null
    var user: User = User()
}

