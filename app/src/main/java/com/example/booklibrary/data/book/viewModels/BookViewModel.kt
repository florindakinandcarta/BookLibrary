package com.example.booklibrary.data.book.viewModels

import androidx.lifecycle.ViewModel
import com.example.booklibrary.data.book.models.Book
import com.example.booklibrary.data.book.models.BookDisplay
import com.example.booklibrary.data.book.repo.BookRepository
import com.example.booklibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {
    suspend fun getAllBooks(): Resource<List<Book>> {
        return bookRepository.getAllBooks()
    }

    suspend fun getBookByISBN(isbn: String): Resource<Book> {
        return bookRepository.getBookByISBN(isbn)
    }

    suspend fun getBooksByTitle(title: String): Resource<List<Book>> {
        return bookRepository.getBooksByTitle(title)
    }

    suspend fun getAvailableBooks(): Resource<List<BookDisplay>> {
        return bookRepository.getAvailableBooks()
    }

    suspend fun getRequestedBooks(): Resource<List<BookDisplay>> {
        return bookRepository.getRequestedBooks()
    }

    suspend fun getBooksByLanguage(language: String): Resource<List<BookDisplay>> {
        return bookRepository.getBooksByLanguage(language)
    }

    suspend fun getBooksByGenre(genre: String): Resource<List<BookDisplay>> {
        return bookRepository.getBooksByGenre(genre)
    }
}