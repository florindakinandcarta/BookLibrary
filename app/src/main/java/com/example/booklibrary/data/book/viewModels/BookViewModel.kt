package com.example.booklibrary.data.book.viewModels

import androidx.compose.ui.util.packFloats
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklibrary.data.book.models.Book
import com.example.booklibrary.data.book.models.BookDisplay
import com.example.booklibrary.data.book.repo.BookRepository
import com.example.booklibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _books = MutableStateFlow<Resource<List<BookDisplay>>>(Resource.Loading())
    val books: StateFlow<Resource<List<BookDisplay>>> = _books

    private val _bookDetails = MutableStateFlow<Resource<Book>?>(Resource.Loading())
    val bookDetails: StateFlow<Resource<Book>?> = _bookDetails

    suspend fun getAllBooks(officeName: String): Resource<List<BookDisplay>> {
        return bookRepository.getAllBooks(officeName)
    }

    suspend fun getBookByISBN(isbn: String, officeName: String) {
        viewModelScope.launch {
            _bookDetails.value = Resource.Loading()
            val result = bookRepository.getBookByISBN(isbn, officeName)
            _bookDetails.value = result
        }
    }

    suspend fun getBooksByTitle(title: String, officeName: String){
      viewModelScope.launch {
          _books.value = Resource.Loading()
          val result = bookRepository.getBooksByTitle(title,officeName)
          _books.value = result
      }
    }

    suspend fun getAvailableBooks(officeName: String){
       viewModelScope.launch {
           _books.value = Resource.Loading()
           val result = bookRepository.getAvailableBooks(officeName)
           _books.value = result
       }
    }

    suspend fun getRequestedBooks(officeName: String): Resource<List<BookDisplay>> {
        return bookRepository.getRequestedBooks(officeName)
    }

    suspend fun getBooksByLanguage(
        language: String,
        officeName: String
    ): Resource<List<BookDisplay>> {
        return bookRepository.getBooksByLanguage(language, officeName)
    }

    suspend fun getBooksByGenre(genre: String, officeName: String): Resource<List<BookDisplay>> {
        return bookRepository.getBooksByGenre(genre, officeName)
    }
}