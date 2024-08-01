package com.example.booklibrary.data.book.viewModels

import androidx.lifecycle.ViewModel
import com.example.booklibrary.data.book.models.response.BookCheckoutResponse
import com.example.booklibrary.data.book.models.response.BookCheckoutWithUserAndBookItemResponse
import com.example.booklibrary.data.book.repo.BookCheckoutQueryRepository
import com.example.booklibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class BookCheckoutQueryViewModel @Inject constructor(
    private val bookCheckoutQueryRepository: BookCheckoutQueryRepository
) : ViewModel() {
    suspend fun getAllBookCheckouts(officeName: String): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        return bookCheckoutQueryRepository.getAllBookCheckouts(officeName)
    }

    suspend fun getAllBookCheckoutsPaginated(
        officeName: String,
        numberOfPages: Int,
        pageSize: Int
    ): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        return bookCheckoutQueryRepository.getAllBookCheckoutsPaginated(
            officeName,
            numberOfPages,
            pageSize
        )
    }

    suspend fun getAllActiveBookCheckouts(officeName: String): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        return bookCheckoutQueryRepository.getAllActiveBookCheckouts(officeName)
    }

    suspend fun getAllPastBookCheckouts(officeName: String): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        return bookCheckoutQueryRepository.getAllPastBookCheckouts(officeName)
    }

    suspend fun getAllBookCheckoutsForBookTitle(
        officeName: String,
        titleSearchTerm: String
    ): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        return bookCheckoutQueryRepository.getAllBookCheckoutsForBookTitle(
            officeName,
            titleSearchTerm
        )
    }

    suspend fun getAllBookCheckoutsForBookTitle(userId: UUID): Resource<List<BookCheckoutResponse>> {
        return bookCheckoutQueryRepository.getAllBookCheckoutsFromUserWithId(userId)
    }
}
