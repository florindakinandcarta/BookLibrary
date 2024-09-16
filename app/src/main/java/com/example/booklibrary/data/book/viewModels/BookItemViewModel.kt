package com.example.booklibrary.data.book.viewModels

import androidx.lifecycle.ViewModel
import com.example.booklibrary.data.book.models.BookID
import com.example.booklibrary.data.book.models.BookItem
import com.example.booklibrary.data.book.repo.BookItemRepository
import com.example.booklibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class BookItemViewModel @Inject constructor(
    private val bookItemRepository: BookItemRepository
) : ViewModel() {
    suspend fun getBookItemsByBookIsbn(isbn: String): Resource<List<BookItem>> {
        return bookItemRepository.getBookItemsByBookIsbn(isbn)
    }

    suspend fun saveBookItem(bookID: BookID): Resource<BookItem> {
        return bookItemRepository.saveBookItem(bookID)
    }

    suspend fun deleteBookItem(id: UUID): Resource<UUID> {
        return bookItemRepository.deleteBookItem(id)
    }

    suspend fun reportBookItemAsDamaged(id: UUID): Resource<UUID> {
        return bookItemRepository.reportBookItemAsDamaged(id)
    }

    suspend fun reportBookItemAsLost(id: UUID): Resource<UUID> {
        return bookItemRepository.reportBookItemAsLost(id)
    }
}