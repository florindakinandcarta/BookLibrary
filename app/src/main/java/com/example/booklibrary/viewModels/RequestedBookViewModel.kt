package com.example.booklibrary.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklibrary.data.models.Book
import com.example.booklibrary.data.models.RequestedBook
import com.example.booklibrary.data.models.request.BookChangeStatus
import com.example.booklibrary.data.models.request.RequestedBookRequestDTO
import com.example.booklibrary.data.models.response.RequestedBookResponse
import com.example.booklibrary.repo.RequestedBookRepository
import com.example.booklibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    private val _book = MutableStateFlow<Resource<RequestedBookResponse>>(Resource.Loading())
    val book: StateFlow<Resource<RequestedBookResponse>> = _book

    private val _bookDetails = MutableStateFlow<Resource<Book>?>(Resource.Loading())
    val bookDetails: StateFlow<Resource<Book>?> = _bookDetails

    private val _message = MutableStateFlow<Resource<String>>(Resource.Loading())
    val message: StateFlow<Resource<String>> = _message


    suspend fun getAllRequestedBooks() {
        viewModelScope.launch {
            when (val result = requestedBookRepository.getAllRequestedBooks()) {
                is Resource.Success -> {
                    _isRefreshing.value = false
                    _books.value = result
                }

                is Resource.Error -> {
                    _isRefreshing.value = false
                    _books.value = result
                }

                is Resource.Loading -> {
                    _isRefreshing.value = true
                    _books.value = Resource.Loading()
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
        when (val result = requestedBookRepository.insertNewRequestedBook(book)) {
            is Resource.Success -> {
                _book.value = result
            }

            is Resource.Error -> {
                _book.value = result
            }

            is Resource.Loading -> {
                _books.value = Resource.Loading()
                _book.value = Resource.Loading()
            }
        }
    }

    suspend fun deleteRequestedBook(bookIsbn: String): Resource<String> {
        return requestedBookRepository.deleteRequestedBook(bookIsbn)
    }


    suspend fun changeBookStatus(
        bookStatus: BookChangeStatus
    ) {
        viewModelScope.launch {
            when (requestedBookRepository.changeBookStatus(bookStatus)) {
                is Resource.Success -> {
                    _message.value = Resource.Success(data = "Book status changed successfully")
                }

                is Resource.Error -> {
                    _message.value = Resource.Error("Something went wrong!")
                }

                is Resource.Loading -> {
                    _books.value = Resource.Loading()
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