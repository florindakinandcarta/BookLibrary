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
    suspend fun getAllBookCheckouts(officeName: String): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        return bookCheckoutRepository.getAllBookCheckouts(officeName)
    }

    suspend fun getAllBookCheckoutsPaginated(
        officeName: String,
        numberOfPages: Int,
        pageSize: Int
    ): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        return bookCheckoutRepository.getAllBookCheckoutsPaginated(
            officeName,
            numberOfPages,
            pageSize
        )
    }

    suspend fun getAllActiveBookCheckouts(officeName: String): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        return bookCheckoutRepository.getAllActiveBookCheckouts(officeName)
    }

    suspend fun getAllPastBookCheckouts(officeName: String): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        return bookCheckoutRepository.getAllPastBookCheckouts(officeName)
    }

    suspend fun getAllBookCheckoutsForBookTitle(
        officeName: String,
        titleSearchTerm: String
    ): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        return bookCheckoutRepository.getAllBookCheckoutsForBookTitle(
            officeName,
            titleSearchTerm
        )
    }

    suspend fun getAllBookCheckoutsForBookTitle(userId: UUID): Resource<List<BookCheckoutResponse>> {
        return bookCheckoutRepository.getAllBookCheckoutsFromUserWithId(userId)
    }

    suspend fun getAllNearReturnDate(officeName: String): Resource<List<BookCheckoutReturnReminderResponse>> {
        return bookCheckoutRepository.getAllNearReturnDate(officeName)
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
