package com.example.booklibrary.data.book.viewModels

import androidx.lifecycle.ViewModel
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

    suspend fun insertBookItem(isbn: String): Resource<BookItem> {
        return bookItemRepository.insertBookItem(isbn)
    }

    suspend fun deleteById(id: UUID): Resource<UUID> {
        return bookItemRepository.deleteById(id)
    }

    suspend fun reportBookItemAsDamaged(bookItemId: UUID): Resource<String> {
        return bookItemRepository.reportBookItemAsDamaged(bookItemId)
    }

    suspend fun reportBookItemAsLost(bookItemId: UUID): Resource<String> {
        return bookItemRepository.reportBookItemAsLost(bookItemId)
    }
}