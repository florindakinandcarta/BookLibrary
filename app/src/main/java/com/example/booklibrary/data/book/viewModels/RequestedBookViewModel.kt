package com.example.booklibrary.data.book.viewModels

import com.example.booklibrary.data.book.models.BookStatus
import com.example.booklibrary.data.book.models.RequestedBook
import com.example.booklibrary.data.book.models.request.BookChangeStatus
import com.example.booklibrary.data.book.models.request.BookRequest
import com.example.booklibrary.data.book.repo.RequestedBookRepository
import com.example.booklibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RequestedBookViewModel @Inject constructor(
    private val requestedBookRepository: RequestedBookRepository
) {
    suspend fun getAllRequestedBooks(officeName: String): Resource<List<RequestedBook>> {
        return requestedBookRepository.getAllRequestedBooks(officeName)
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

    suspend fun deleteRequestedBook(bookIsbn: String, officeName: String): Resource<String> {
        return requestedBookRepository.deleteRequestedBook(bookIsbn, officeName)
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
        status: BookStatus,
        officeName: String
    ): Resource<List<RequestedBook>> {
        return requestedBookRepository.getRequestedBooksByBookStatus(status, officeName)
    }
}