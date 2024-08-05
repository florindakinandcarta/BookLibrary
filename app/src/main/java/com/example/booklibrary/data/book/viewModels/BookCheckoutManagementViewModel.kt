package com.example.booklibrary.data.book.viewModels

import androidx.lifecycle.ViewModel
import com.example.booklibrary.data.book.models.request.BookCheckoutRequest
import com.example.booklibrary.data.book.models.response.BookCheckoutResponse
import com.example.booklibrary.data.book.repo.BookCheckoutManagementRepository
import com.example.booklibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookCheckoutManagementViewModel @Inject constructor(
    private val bookCheckoutManagementRepository: BookCheckoutManagementRepository
) : ViewModel() {
    suspend fun borrowBookItem(bookCheckoutRequest: BookCheckoutRequest): Resource<BookCheckoutResponse> {
        return bookCheckoutManagementRepository.borrowBookItem(bookCheckoutRequest)
    }


    suspend fun returnBookItem(bookCheckoutRequest: BookCheckoutRequest): Resource<BookCheckoutResponse> {
        return bookCheckoutManagementRepository.returnBookItem(bookCheckoutRequest)
    }

}