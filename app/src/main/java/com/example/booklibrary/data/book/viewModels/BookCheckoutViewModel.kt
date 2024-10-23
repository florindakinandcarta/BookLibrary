package com.example.booklibrary.data.book.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklibrary.data.book.models.Book
import com.example.booklibrary.data.book.models.request.BookCheckoutRequest
import com.example.booklibrary.data.book.models.response.BookCheckoutResponse
import com.example.booklibrary.data.book.models.response.BookCheckoutReturnReminderResponse
import com.example.booklibrary.data.book.models.response.BookCheckoutWithUserAndBookItemResponse
import com.example.booklibrary.data.book.repo.BookCheckoutRepository
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

    suspend fun getAllBookCheckouts(){
        viewModelScope.launch {
            _books.value = Resource.Loading()
            val result = bookCheckoutRepository.getAllBookCheckouts()
            _books.value = result
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

    suspend fun getAllActiveBookCheckouts(): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        return bookCheckoutRepository.getAllActiveBookCheckouts()
    }

    suspend fun getAllPastBookCheckouts(): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        return bookCheckoutRepository.getAllPastBookCheckouts()
    }

    suspend fun getAllBookCheckoutsForBookTitle(
        titleSearchTerm: String
    ): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        return bookCheckoutRepository.getAllBookCheckoutsForBookTitle(
            titleSearchTerm
        )
    }

    suspend fun getAllBookCheckoutsForBookTitle(userId: UUID): Resource<List<BookCheckoutResponse>> {
        return bookCheckoutRepository.getAllBookCheckoutsFromUserWithId(userId)
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

    suspend fun borrowBookItem(bookCheckoutRequest: BookCheckoutRequest): Resource<BookCheckoutResponse> {
        return bookCheckoutRepository.borrowBookItem(bookCheckoutRequest)
    }

    suspend fun returnBookItem(bookCheckoutRequest: BookCheckoutRequest): Resource<BookCheckoutResponse> {
        return bookCheckoutRepository.returnBookItem(bookCheckoutRequest)
    }
}
