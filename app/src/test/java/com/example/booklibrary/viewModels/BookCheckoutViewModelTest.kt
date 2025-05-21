package com.example.booklibrary.viewModels

import app.cash.turbine.test
import com.example.booklibrary.data.models.response.BookCheckoutWithUserAndBookItemResponse
import com.example.booklibrary.repo.BookCheckoutRepository
import com.example.booklibrary.util.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

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

    @Test
    fun getAllBookCheckouts_Emits_Loading_then_Success() = runTest {
        val expectedBookCheckouts = listOf(
            BookCheckoutWithUserAndBookItemResponse(
                userFullName = "Florinda Hasani",
                bookItemId = "123456",
                bookTitle = "The Catcher in the Rye",
                bookISBN = "987654321",
                dateBorrowed = "12-04-2024",
                dateReturned = "22-04-2024",
                scheduledReturnDate = "23-04-2024"
            )
        )

        coEvery { bookCheckoutRepository.getAllBookCheckouts() } returns Resource.Success(
            expectedBookCheckouts
        )
        bookCheckoutViewModel.books.test {
            val initialLoading = awaitItem()
            assertTrue(initialLoading is Resource.Loading)

            bookCheckoutViewModel.getAllBookCheckouts()

            val successResult = awaitItem()
            assertTrue(successResult is Resource.Success)
            assertEquals(expectedBookCheckouts, (successResult as Resource.Success).data)
            expectNoEvents()
        }
        coVerify(exactly = 1) { bookCheckoutRepository.getAllBookCheckouts() }
    }


    @Test
    fun getAllBookCheckoutsForBookTitle_withEmptyTitle_shouldNotCallRepository() = runTest {
        val testTitle = ""
        val errorMessage = "Title cannot be empty. Please provide a valid book title!"
        bookCheckoutViewModel.getAllBookCheckoutsForBookTitle(testTitle)
        coVerify(exactly = 0) { bookCheckoutRepository.getAllBookCheckoutsForBookTitle(testTitle) }
        assertTrue(bookCheckoutViewModel.books.value is Resource.Error)
        assertEquals(
            errorMessage,
            (bookCheckoutViewModel.books.value as Resource.Error).message
        )
    }
}