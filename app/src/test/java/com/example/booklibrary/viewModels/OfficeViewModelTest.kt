package com.example.booklibrary.viewModels

import app.cash.turbine.test
import com.example.booklibrary.data.models.response.OfficeResponse
import com.example.booklibrary.repo.OfficeRepository
import com.example.booklibrary.util.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class OfficeViewModelTest {

    private lateinit var officeRepository: OfficeRepository
    private lateinit var viewmodel: OfficeViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        officeRepository = mockk()
        viewmodel = OfficeViewModel(officeRepository)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getAllOffices_emits_Loading_then_Success() = runTest {
        val sampleOfficeList = listOf(OfficeResponse("Prishtina"))

        coEvery { officeRepository.getAllOffices() } returns Resource.Success(sampleOfficeList)

        viewmodel.offices.test {
            val initialLoading = awaitItem()
            assertTrue(initialLoading is Resource.Loading)

            viewmodel.getAllOffices()

            val loadingBeforeFetch = awaitItem()
            assertTrue(loadingBeforeFetch is Resource.Loading)

            val successResult = awaitItem()
            assertTrue(successResult is Resource.Success)
            assertEquals(sampleOfficeList, (successResult as Resource.Success).data)

            expectNoEvents()
        }
        coVerify(exactly = 1) { officeRepository.getAllOffices() }
    }


    @Test
    fun getAllOffices_emits_Loading_then_Error() = runTest {
        val errorMessage = "Network error occurred"
        coEvery { officeRepository.getAllOffices() } returns Resource.Error(errorMessage)

        viewmodel.offices.test {
            val initialLoading = awaitItem()
            assertTrue(initialLoading is Resource.Loading)

            viewmodel.getAllOffices()

            val loadingBeforeFetch = awaitItem()
            assertTrue(loadingBeforeFetch is Resource.Loading)

            val errorResult = awaitItem()
            assertEquals(errorMessage, (errorResult as Resource.Error).message)

            expectNoEvents()
        }
        coVerify(exactly = 1) { officeRepository.getAllOffices() }
    }
}