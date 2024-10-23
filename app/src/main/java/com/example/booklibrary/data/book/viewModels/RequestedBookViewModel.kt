package com.example.booklibrary.data.book.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklibrary.data.book.models.Book
import com.example.booklibrary.data.book.models.RequestedBook
import com.example.booklibrary.data.book.models.request.BookChangeStatus
import com.example.booklibrary.data.book.models.request.BookRequest
import com.example.booklibrary.data.book.repo.RequestedBookRepository
import com.example.booklibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RequestedBookViewModel @Inject constructor(
    private val requestedBookRepository: RequestedBookRepository
) : ViewModel() {
    private val _books = MutableStateFlow<Resource<List<RequestedBook>>>(Resource.Loading())
    val books: StateFlow<Resource<List<RequestedBook>>> = _books

    private val _bookDetails = MutableStateFlow<Resource<Book>?>(Resource.Loading())
    val bookDetails: StateFlow<Resource<Book>?> = _bookDetails

    suspend fun getAllRequestedBooks() {
        viewModelScope.launch {
            _books.value = Resource.Loading()
            val result = requestedBookRepository.getAllRequestedBooks()
            _books.value = result
        }
    }

    suspend fun getRequestedBookById(id: UUID): Resource<RequestedBook> {
        return requestedBookRepository.getRequestedBookById(id)
    }

    suspend fun getRequestedBookByISBN(isbn: String): Resource<RequestedBook> {
        return requestedBookRepository.getRequestedBookByISBN(isbn)
    }

    suspend fun insertNewRequestedBook(book: BookRequest): Resource<RequestedBook> {
        return requestedBookRepository.insertNewRequestedBook(book)
    }

    suspend fun deleteRequestedBook(bookIsbn: String): Resource<String> {
        return requestedBookRepository.deleteRequestedBook(bookIsbn)
    }


    suspend fun changeBookStatus(
        bookStatus: BookChangeStatus
    ): Resource<RequestedBook> {
        return requestedBookRepository.changeBookStatus(bookStatus)
    }

    suspend fun handleRequestedBookLike(
        status: BookRequest
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