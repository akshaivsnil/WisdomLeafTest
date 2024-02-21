package com.akshai.tutorials.wisdomleaftest.viewModels

import com.akshai.tutorials.wisdomleaftest.MainDispatcherRule
import com.akshai.tutorials.wisdomleaftest.getOrAwaitValueTest
import com.akshai.tutorials.wisdomleaftest.repository.MainRepo
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class MainViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: MainViewModel

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @Inject
    lateinit var mainRepo: MainRepo

    @Before
    fun setUp() {
        hiltRule.inject()

        viewModel = MainViewModel(mainRepo)
        viewModel.responseLiveData.observeForever {
            println(">>>>>>>${it}")
        }
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getListFromRepo() = runTest {
        Assert.assertNotNull(mainRepo)
        viewModel.getListFromRepo()
        advanceUntilIdle()
        val result = viewModel.responseLiveData.getOrAwaitValueTest()
        assertEquals(result, "")
    }
}