package com.example.booklibrary.data.book.viewModels

import androidx.lifecycle.ViewModel
import com.example.booklibrary.data.book.models.request.BookCheckoutRequest
import com.example.booklibrary.data.book.models.response.BookCheckoutResponse
import com.example.booklibrary.data.book.models.response.BookCheckoutReturnReminderResponse
import com.example.booklibrary.data.book.models.response.BookCheckoutWithUserAndBookItemResponse
import com.example.booklibrary.data.book.repo.BookCheckoutRepository
import com.example.booklibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class BookCheckoutViewModel @Inject constructor(
    private val bookCheckoutRepository: BookCheckoutRepository
) : ViewModel() {
    suspend fun getAllBookCheckouts(): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        return bookCheckoutRepository.getAllBookCheckouts()
    }

    suspend fun getAllBookCheckoutsPaginated(
        numberOfPages: Int,
        pageSize: Int
    ): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        return bookCheckoutRepository.getAllBookCheckoutsPaginated(
            numberOfPages,
            pageSize
        )
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
