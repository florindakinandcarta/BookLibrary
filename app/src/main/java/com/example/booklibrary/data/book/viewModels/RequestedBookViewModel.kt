package com.example.booklibrary.data.book.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklibrary.data.book.models.Book
import com.example.booklibrary.data.book.models.RequestedBook
import com.example.booklibrary.data.book.models.request.BookChangeStatus
import com.example.booklibrary.data.book.models.request.RequestedBookRequestDTO
import com.example.booklibrary.data.book.models.response.RequestedBookResponse
import com.example.booklibrary.data.book.repo.RequestedBookRepository
import com.example.booklibrary.util.Resource
import com.example.booklibrary.util.getUserJWTToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RequestedBookViewModel @Inject constructor(
    private val requestedBookRepository: RequestedBookRepository,
) : ViewModel() {
    private val _books = MutableStateFlow<Resource<List<RequestedBook>>>(Resource.Loading())
    val books: StateFlow<Resource<List<RequestedBook>>> = _books
    private val _isRefreshing = MutableStateFlow(true)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    private val _book = MutableStateFlow<Resource<RequestedBookResponse>>(Resource.Loading())
    val book: StateFlow<Resource<RequestedBookResponse>> = _book

    private val _bookDetails = MutableStateFlow<Resource<Book>?>(Resource.Loading())
    val bookDetails: StateFlow<Resource<Book>?> = _bookDetails

    private val _message = MutableStateFlow<Resource<String>>(Resource.Loading())
    val message: StateFlow<Resource<String>> = _message


    suspend fun getAllRequestedBooks() {
        viewModelScope.launch {
            when( val result = requestedBookRepository.getAllRequestedBooks()){
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

    suspend fun getRequestedBookById(id: UUID): Resource<RequestedBook> {
        return requestedBookRepository.getRequestedBookById(id)
    }

    suspend fun getRequestedBookByISBN(isbn: String): Resource<RequestedBook> {
        return requestedBookRepository.getRequestedBookByISBN(isbn)
    }

    suspend fun insertNewRequestedBook(book: RequestedBookRequestDTO) {
        viewModelScope.launch {
            _book.value = Resource.Loading()
            val result = requestedBookRepository.insertNewRequestedBook(book)
            _book.value = result
        }
    }

    suspend fun deleteRequestedBook(bookIsbn: String): Resource<String> {
        return requestedBookRepository.deleteRequestedBook(bookIsbn)
    }


    suspend fun changeBookStatus(
        bookStatus: BookChangeStatus
    ){
        viewModelScope.launch {
            when(requestedBookRepository.changeBookStatus(bookStatus)){
                is Resource.Success -> {
                    _message.value = Resource.Success(data = "Book status changed successfully")
                }
                is Resource.Error -> {
                    _message.value = Resource.Error("Something went wrong!")
                }
                is Resource.Loading -> {

                }
            }

        }
    }

    suspend fun handleRequestedBookLike(
        status: RequestedBookRequestDTO
    ): Resource<RequestedBook> {
        return requestedBookRepository.handleRequestedBookLike(status)
    }


    suspend fun getRequestedBooksByBookStatus(
        status: String
    ) {
        viewModelScope.launch {
            _books.value = Resource.Loading()
            val result = requestedBookRepository.getRequestedBooksByBookStatus(status)
            _books.value = result
        }
    }
}