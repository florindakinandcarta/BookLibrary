package com.example.booklibrary.viewModels

import com.example.booklibrary.repo.BookCheckoutRepository
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

class BookCheckoutViewModelTest {

    private lateinit var bookCheckoutRepository: BookCheckoutRepository
    private lateinit var bookCheckoutViewModel: BookCheckoutViewModel
    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        bookCheckoutRepository = mockk()
        bookCheckoutViewModel = BookCheckoutViewModel(bookCheckoutRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}