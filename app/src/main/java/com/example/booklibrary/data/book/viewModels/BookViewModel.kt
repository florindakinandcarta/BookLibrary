package com.example.booklibrary.data.book.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklibrary.data.book.models.Book
import com.example.booklibrary.data.book.models.BookDisplay
import com.example.booklibrary.data.book.repo.BookRepository
import com.example.booklibrary.data.googleBooks.GoogleBooks
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

    private val _isRefreshing = MutableStateFlow(true)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    private val _bookDetails = MutableStateFlow<Resource<Book>?>(Resource.Loading())
    val bookDetails: StateFlow<Resource<Book>?> = _bookDetails

    suspend fun getAllBooks(){
        viewModelScope.launch {
            when( val result = bookRepository.getAllBooks()){
                is Resource.Success -> {
                    _books.value = result
                    _isRefreshing.value = false
                }
                is Resource.Error -> {
                    _books.value = result
                    _isRefreshing.value = false
                }
                is Resource.Loading -> {
                    _isRefreshing.value = true
                }
            }
        }
    }

    suspend fun getBookByISBN(isbn: String) {
        viewModelScope.launch {
            _bookDetails.value = Resource.Loading()
            val result = bookRepository.getBookByISBN(isbn)
            _bookDetails.value = result
        }
    }

    suspend fun getBooksByTitle(title: String) {
        viewModelScope.launch {
            _books.value = Resource.Loading()
            val result = bookRepository.getBooksByTitle(title)
            _books.value = result
        }
    }
    fun clearSearchResults() {
        _books.value = Resource.Success(emptyList())
    }

    suspend fun getAvailableBooks() {
        viewModelScope.launch {
            _books.value = Resource.Loading()
            val result = bookRepository.getAvailableBooks()
            _books.value = result
        }
    }

    suspend fun getRequestedBooks() {
        viewModelScope.launch {
            _books.value = Resource.Loading()
            val result = bookRepository.getRequestedBooks()
            _books.value = result
        }
    }

    suspend fun getBooksByLanguage(
        language: String,
    ) {
        when( val result = bookRepository.getBooksByLanguage(language)){
            is Resource.Success -> {
                _books.value = result
                _isRefreshing.value = false
            }
            is Resource.Error -> {
                _books.value = result
                _isRefreshing.value = false
            }
            is Resource.Loading -> {
                _isRefreshing.value = true
            }
        }
    }

    suspend fun getBooksByGenre(genre: String) {
        viewModelScope.launch {
            _books.value = Resource.Loading()
            val result = bookRepository.getBooksByGenre(genre)
            _books.value = result
        }
    }
}