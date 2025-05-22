package com.example.booklibrary.viewModels

import app.cash.turbine.test
import com.example.booklibrary.data.models.RequestedBook
import com.example.booklibrary.data.models.request.BookChangeStatus
import com.example.booklibrary.repo.RequestedBookRepository
import com.example.booklibrary.util.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RequestedBookViewModelTest {

    private lateinit var requestedBookRepository: RequestedBookRepository
    private lateinit var requestedBookViewModel: RequestedBookViewModel
    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        requestedBookRepository = mockk()
        requestedBookViewModel = RequestedBookViewModel(requestedBookRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun getAllRequestedBooks_whenSuccessAndError_isRefreshShouldBeFalse() = runTest {
        val listOfRequestedBooksTest = listOf(
            RequestedBook(
                id = "1234567",
                bookISBN = "98765432",
                officeName = "Prishtina",
                requestedDate = "24-04-2024",
                likeCounter = 5,
                bookStatus = "Requested",
                title = "The Catcher in the Rye",
                image = "http://www.wert.com/image.jpg"
            )
        )
        coEvery { requestedBookRepository.getAllRequestedBooks() } returns Resource.Success(
            listOfRequestedBooksTest
        )
        requestedBookViewModel.getAllRequestedBooks()
        assertEquals(false, requestedBookViewModel.isRefreshing.value)

        coEvery { requestedBookRepository.getAllRequestedBooks() } returns Resource.Error()
        requestedBookViewModel.getAllRequestedBooks()
        assertEquals(false, requestedBookViewModel.isRefreshing.value)
    }

    @Test
    fun getAllRequestedBooks_whenLoading_isRefreshShouldBeTrue() = runTest {
        coEvery { requestedBookRepository.getAllRequestedBooks() } coAnswers { Resource.Loading() }
        requestedBookViewModel.isRefreshing.drop(1).test {
            requestedBookViewModel.getAllRequestedBooks()
            assertEquals(true, awaitItem())
        }
    }

    @Test
    fun changeBookStatus_shouldReturnSuccess() = runTest {
        val bookStatusTest = BookChangeStatus(
            requestedBookId = "987656789",
            newBookStatus = "Approved"
        )
        val dummyRequestedBook = RequestedBook(
            id = "1234567",
            bookISBN = "98765432",
            officeName = "Prishtina",
            requestedDate = "24-04-2024",
            likeCounter = 5,
            bookStatus = "Approved",
            title = "The Catcher in the Rye",
            image = "http://www.wert.com/image.jpg"
        )
        coEvery { requestedBookRepository.changeBookStatus(bookStatusTest) } returns Resource.Success(
            dummyRequestedBook
        )
        requestedBookViewModel.changeBookStatus(bookStatusTest)
        advanceUntilIdle()
        assertTrue(requestedBookViewModel.message.value is Resource.Success)
        val successResource = requestedBookViewModel.message.value as Resource.Success<String>
        assertEquals("Book status changed successfully", successResource.data)
    }
}