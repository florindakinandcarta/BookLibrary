package com.example.booklibrary.ui.requested

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklibrary.data.googleBooks.ExceptionResponse
import com.example.booklibrary.data.googleBooks.GoogleBooks
import com.example.booklibrary.data.googleBooks.GoogleService
import com.example.booklibrary.util.Resource
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class GoogleBooksViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val googleBooksApi: GoogleService
) : ViewModel() {

    private val _responseBooks = MutableStateFlow<Resource<GoogleBooks>?>(null)
    val responseBooks: StateFlow<Resource<GoogleBooks>?> = _responseBooks

    private val _loadingBooks = MutableStateFlow(false)
    val loadingBooks: StateFlow<Boolean> = _loadingBooks

    private val _isSaved = MutableStateFlow(false)
    val isSaved: StateFlow<Boolean> = _isSaved

    private val _isResponseZero = MutableStateFlow(false)
    val isResponseZero: StateFlow<Boolean> = _isResponseZero

    fun fetchBooks(queryBookName: String?, loadMore: Int) {
        _loadingBooks.value = true
        viewModelScope.launch {
            try {
                val response = googleBooksApi.getGoogleBooks(queryBookName, maxResults = loadMore + 20)
                _responseBooks.value = Resource.Success(response)
            } catch (httpException: HttpException) {
                val errorResponse = Gson().fromJson(
                    httpException.response()?.errorBody()?.string(),
                    ExceptionResponse::class.java
                )
                _responseBooks.value = Resource.Error(errorResponse?.message ?: "")
            } catch (e: Exception) {
                _responseBooks.value = Resource.Error()
            } finally {
                _loadingBooks.value = false
            }
        }
    }
    fun fetchBookByISBN(bookISBN: String) {
        _loadingBooks.value = true
        viewModelScope.launch {
            try {
                val response = googleBooksApi.getGoogleBooksWithISBN(bookISBN)
                _responseBooks.value = Resource.Success(response)
            } catch (httpException: HttpException) {
                val errorResponse = Gson().fromJson(
                    httpException.response()?.errorBody()?.string(),
                    ExceptionResponse::class.java
                )
                _responseBooks.value = Resource.Error(errorResponse?.message ?: "")
            } catch (e: Exception) {
                _responseBooks.value = Resource.Error()
            } finally {
                _loadingBooks.value = false
            }
        }
    }
    fun clearSearchResults() {
        _responseBooks.value = Resource.Success(GoogleBooks())
        _isResponseZero.value = false
    }

}