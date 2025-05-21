package com.example.booklibrary.viewModels

import androidx.lifecycle.ViewModel
import com.example.booklibrary.data.models.BookID
import com.example.booklibrary.data.models.BookItem
import com.example.booklibrary.repo.BookItemRepository
import com.example.booklibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class BookItemViewModel @Inject constructor(
    private val bookItemRepository: BookItemRepository
) : ViewModel() {

    private val _bookItemResponse = MutableStateFlow<Resource<BookItem>>(Resource.Loading())
    val bookItemResponse: StateFlow<Resource<BookItem>> = _bookItemResponse

    private val _bookItemsResponse = MutableStateFlow<Resource<List<BookItem>>>(Resource.Loading())
    val bookItemsResponse: StateFlow<Resource<List<BookItem>>> = _bookItemsResponse

    private val _message = MutableStateFlow<Resource<String>>(Resource.Loading())
    val message: StateFlow<Resource<String>> = _message

    suspend fun getBookItemsByBookIsbn(isbn: String) {
        when (val result = bookItemRepository.getBookItemsByBookIsbn(isbn)) {
            is Resource.Success -> {
                _bookItemsResponse.value = result
            }

            is Resource.Error -> {
                _bookItemsResponse.value = result
            }

            is Resource.Loading -> {
                _bookItemsResponse.value = Resource.Loading()
            }
        }
    }

    suspend fun createBookItem(bookISBN: BookID) {
        when (val result = bookItemRepository.createBookItem(bookISBN)) {
            is Resource.Success -> {
                _bookItemResponse.value = result
                _message.value = Resource.Success("Book item created successfully")
            }

            is Resource.Error -> {
                _bookItemResponse.value = result
                _message.value = Resource.Error(result.message.toString())
            }

            is Resource.Loading -> {
                _bookItemResponse.value = Resource.Loading()
            }
        }
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