package com.akshai.tutorials.wisdomleaftest.viewModels


import com.akshai.tutorials.wisdomleaftest.network.ApiHelper
import com.akshai.tutorials.wisdomleaftest.repository.DataHandler
import com.akshai.tutorials.wisdomleaftest.repository.MainRepo
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTestUsingMockk {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
    }


    fun tearDown() {
    }

    @Test
    fun getListFromRepo() = runTest {
        val apiHelper: ApiHelper = mockk<ApiHelper>(relaxed = true)
        val repository = MainRepo(apiHelper)
        viewModel = MainViewModel(repository)
        coEvery {
            viewModel.getListFromRepo()
        }
//        Assert.assertEquals(DataHandler.LOADING, viewModel.responseLiveData.value)
        Assert.assertEquals(DataHandler.ERROR(message = "Data not Found"), viewModel.responseLiveData.value)
    }
}