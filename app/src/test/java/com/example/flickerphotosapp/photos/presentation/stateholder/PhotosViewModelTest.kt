package com.example.flickerphotosapp.photos.presentation.stateholder

import com.example.flickerphotosapp.photos.domain.entity.PhotoEntity
import com.example.flickerphotosapp.photos.domain.usecase.GetPhotosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class
PhotosViewModelTest {
    private lateinit var viewModel: PhotosViewModel

    @Mock
    private lateinit var getPhotosUseCase: GetPhotosUseCase
    private val testScheduler = TestCoroutineScheduler()
    private lateinit var dispatcher: TestDispatcher

    @Mock
    private lateinit var photoEntity: PhotoEntity

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        dispatcher = StandardTestDispatcher(testScheduler)
        Dispatchers.setMain(dispatcher)
        viewModel = PhotosViewModel(
             getPhotosUseCase = getPhotosUseCase
        )
    }

    @Test
    fun `executing GetDetails, and if getSearchUseCase throws an exception it should return Error`() =
        runTest {
            //Arrange
            whenever(getPhotosUseCase.invoke("Color", 1)).thenReturn(emptyFlow())

            //Action
            val result = viewModel.getPhotos("Color", 1)
            advanceUntilIdle()

            //Assert
            assertEquals(result, emptyFlow<PhotoEntity>())
        }
}
