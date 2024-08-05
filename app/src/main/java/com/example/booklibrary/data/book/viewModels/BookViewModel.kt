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
    suspend fun getAllBooks(officeName: String): Resource<List<BookDisplay>> {
        return bookRepository.getAllBooks(officeName)
    }

    suspend fun getBookByISBN(isbn: String, officeName: String): Resource<Book> {
        return bookRepository.getBookByISBN(isbn, officeName)
    }

    suspend fun getBooksByTitle(title: String, officeName: String): Resource<List<BookDisplay>> {
        return bookRepository.getBooksByTitle(title, officeName)
    }

    suspend fun getAvailableBooks(officeName: String): Resource<List<BookDisplay>> {
        return bookRepository.getAvailableBooks(officeName)
    }

    suspend fun getRequestedBooks(officeName: String): Resource<List<BookDisplay>> {
        return bookRepository.getRequestedBooks(officeName)
    }

    suspend fun getBooksByLanguage(
        language: String,
        officeName: String
    ): Resource<List<BookDisplay>> {
        return bookRepository.getBooksByLanguage(language, officeName)
    }

    suspend fun getBooksByGenre(genre: String, officeName: String): Resource<List<BookDisplay>> {
        return bookRepository.getBooksByGenre(genre, officeName)
    }
}