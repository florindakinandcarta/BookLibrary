package com.example.booklibrary.data.book.viewModels

import com.example.booklibrary.data.book.models.BookStatus
import com.example.booklibrary.data.book.models.RequestedBook
import com.example.booklibrary.data.book.repo.RequestedBookRepository
import com.example.booklibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RequestedBookViewModel @Inject constructor(
    private val requestedBookRepository: RequestedBookRepository
) {
    suspend fun getAllRequestedBooks(): Resource<List<RequestedBook>> {
        return requestedBookRepository.getAllRequestedBooks()
    }

    suspend fun getRequestedBookById(id: UUID): Resource<RequestedBook> {
        return requestedBookRepository.getRequestedBookById(id)
    }

    suspend fun getRequestedBookByISBN(isbn: String): Resource<RequestedBook> {
        return requestedBookRepository.getRequestedBookByISBN(isbn)
    }

    suspend fun saveRequestedBook(bookIsbn: String): Resource<RequestedBook> {
        return requestedBookRepository.saveRequestedBook(bookIsbn)
    }

    suspend fun deleteRequestedBook(bookIsbn: String): Resource<String> {
        return requestedBookRepository.deleteRequestedBook(bookIsbn)
    }


    suspend fun changeBookStatus(
        requestedBookId: UUID,
        bookStatus: BookStatus
    ): Resource<RequestedBook> {
        return requestedBookRepository.changeBookStatus(requestedBookId, bookStatus)
    }

    suspend fun handleRequestedBookLike(requestedBookId: UUID): Resource<RequestedBook> {
        return requestedBookRepository.handleRequestedBookLike(requestedBookId)
    }


    suspend fun getRequestedBooksByBookStatus(status: BookStatus): Resource<List<RequestedBook>> {
        return requestedBookRepository.getRequestedBooksByBookStatus(status)
    }
}