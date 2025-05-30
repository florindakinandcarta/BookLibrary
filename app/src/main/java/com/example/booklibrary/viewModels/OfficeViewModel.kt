package com.example.booklibrary.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklibrary.data.models.response.OfficeResponse
import com.example.booklibrary.repo.OfficeRepository
import com.example.booklibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfficeViewModel @Inject constructor(
    private val officeRepository: OfficeRepository
) : ViewModel() {
    private val _offices = MutableStateFlow<Resource<List<OfficeResponse>>>(Resource.Loading())
    val offices: StateFlow<Resource<List<OfficeResponse>>> = _offices
    suspend fun getAllOffices() {
        viewModelScope.launch {
            _offices.value = Resource.Loading()
            val result = officeRepository.getAllOffices()
            _offices.value = result
        }
    }
}