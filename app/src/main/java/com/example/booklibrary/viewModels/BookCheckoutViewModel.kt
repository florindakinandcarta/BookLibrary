package com.example.booklibrary.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklibrary.data.models.Book
import com.example.booklibrary.data.models.request.BookCheckoutRequest
import com.example.booklibrary.data.models.request.BookReturnRequest
import com.example.booklibrary.data.models.response.BookCheckoutResponse
import com.example.booklibrary.data.models.response.BookCheckoutReturnReminderResponse
import com.example.booklibrary.data.models.response.BookCheckoutWithUserAndBookItemResponse
import com.example.booklibrary.repo.BookCheckoutRepository
import com.example.booklibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class BookCheckoutViewModel @Inject constructor(
    private val bookCheckoutRepository: BookCheckoutRepository
) : ViewModel() {
    private val _books =
        MutableStateFlow<Resource<List<BookCheckoutWithUserAndBookItemResponse>>>(Resource.Loading())
    val books: StateFlow<Resource<List<BookCheckoutWithUserAndBookItemResponse>>> = _books

    private val _bookDetails = MutableStateFlow<Resource<Book>?>(Resource.Loading())
    val bookDetails: StateFlow<Resource<Book>?> = _bookDetails

    private val _bookCheckouts =
        MutableStateFlow<Resource<List<BookCheckoutResponse>>>(Resource.Loading())
    val bookCheckouts: StateFlow<Resource<List<BookCheckoutResponse>>> = _bookCheckouts

    private val _bookReturnResponse =
        MutableStateFlow<Resource<BookCheckoutResponse>>(Resource.Loading())
    val bookReturnResponse: StateFlow<Resource<BookCheckoutResponse>> = _bookReturnResponse

    private val _checkoutMessage = MutableStateFlow<Resource<String>>(Resource.Loading())
    val checkoutMessage: StateFlow<Resource<String>> = _checkoutMessage

    suspend fun getAllBookCheckouts() {
        viewModelScope.launch {
            when (val result = bookCheckoutRepository.getAllBookCheckouts()) {
                is Resource.Success -> {
                    _books.value = result
                }

                is Resource.Error -> {
                    _books.value = result
                }

                is Resource.Loading -> {
                    _books.value = Resource.Loading()
                }
            }
        }
    }

    suspend fun getAllBookCheckoutsPaginated(
        numberOfPages: Int,
        pageSize: Int
    ) {
        viewModelScope.launch {
            _books.value = Resource.Loading()
            val result = bookCheckoutRepository.getAllBookCheckoutsPaginated(
                numberOfPages,
                pageSize
            )
            _books.value = result
        }
    }

    suspend fun getAllActiveBookCheckouts() {
        when (val result = bookCheckoutRepository.getAllActiveBookCheckouts()) {
            is Resource.Success -> {
                _books.value = result
            }

            is Resource.Error -> {
                _books.value = result
            }

            is Resource.Loading -> {
                _books.value = Resource.Loading()
            }
        }
    }

    suspend fun getAllPastBookCheckouts() {
        when (val result = bookCheckoutRepository.getAllPastBookCheckouts()) {
            is Resource.Success -> {
                _books.value = result
            }

            is Resource.Error -> {
                _books.value = result
            }

            is Resource.Loading -> {
                _books.value = Resource.Loading()
            }
        }
    }

    suspend fun getAllBookCheckoutsForBookTitle(
        titleSearchTerm: String
    ) {
        if (titleSearchTerm.isBlank()) {
            _books.value = Resource.Error("Title cannot be empty. Please provide a valid book title!")
            return
        }
        when (val result =
            bookCheckoutRepository.getAllBookCheckoutsForBookTitle(titleSearchTerm)) {
            is Resource.Success -> {
                _books.value = result
                println("result $result")
            }

            is Resource.Error -> {
                _books.value = result
                println("error ${result.message}")

            }

            is Resource.Loading -> {
                _books.value = Resource.Loading()
            }
        }
    }

    suspend fun getAllBookCheckoutsForUser() {
        when (val result = bookCheckoutRepository.getAllBookCheckoutsForUser()) {
            is Resource.Success -> {
                _books.value = Resource.Loading()
                _bookCheckouts.value = result
            }

            is Resource.Error -> {
                _bookCheckouts.value = result
            }

            is Resource.Loading -> {
                _bookCheckouts.value = Resource.Loading()
            }
        }
    }

    suspend fun getAllNearReturnDate(): Resource<List<BookCheckoutReturnReminderResponse>> {
        return bookCheckoutRepository.getAllNearReturnDate()
    }

    suspend fun getAllBooksForUserByTitleContaining(
        userId: UUID,
        titleSearchTerm: String
    ): Resource<List<BookCheckoutResponse>> {
        return bookCheckoutRepository.getAllBooksForUserByTitleContaining(
            userId,
            titleSearchTerm
        )
    }

    suspend fun borrowBookItem(bookCheckoutRequest: BookCheckoutRequest) {
        when (bookCheckoutRepository.borrowBookItem(bookCheckoutRequest)) {
            is Resource.Success -> {
                _checkoutMessage.value =
                    Resource.Error(message = "Book borrowed successfully")
            }

            is Resource.Error -> {
                _checkoutMessage.value =
                    Resource.Error(message = "Book is not available to borrow")
            }

            is Resource.Loading -> {
                _checkoutMessage.value = Resource.Loading()
            }
        }

    }

    suspend fun returnBookItem(bookCheckoutRequest: BookReturnRequest) {
        when (val result = bookCheckoutRepository.returnBookItem(bookCheckoutRequest)) {
            is Resource.Success -> {
                _bookReturnResponse.value = result
            }

            is Resource.Error -> {
                _bookReturnResponse.value = result
            }

            is Resource.Loading -> {
                _bookReturnResponse.value = Resource.Loading()
            }
        }
    }
}
