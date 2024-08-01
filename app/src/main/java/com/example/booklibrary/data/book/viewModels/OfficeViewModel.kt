package com.example.booklibrary.data.book.viewModels

import androidx.lifecycle.ViewModel
import com.example.booklibrary.data.book.models.response.OfficeResponse
import com.example.booklibrary.data.book.repo.OfficeRepository
import com.example.booklibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OfficeViewModel @Inject constructor(
    private val officeRepository: OfficeRepository
) : ViewModel() {
    suspend fun getAllOffices(): Resource<List<OfficeResponse>> {
        return officeRepository.getAllOffices()
    }
}