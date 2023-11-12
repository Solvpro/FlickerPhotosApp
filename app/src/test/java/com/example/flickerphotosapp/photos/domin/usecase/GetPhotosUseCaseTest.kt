package com.example.flickerphotosapp.photos.domin.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.example.flickerphotosapp.photos.domain.entity.PhotoEntity
import com.example.flickerphotosapp.photos.domain.repository.PhotosRepository
import com.example.flickerphotosapp.photos.domain.usecase.GetPhotosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalPagingApi
class GetPhotosUseCaseTest {
    private lateinit var getPhotosUseCase: GetPhotosUseCase

    @Mock
    private lateinit var repository: PhotosRepository
    private val testScheduler = TestCoroutineScheduler()
    private lateinit var dispatcher: TestDispatcher

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        dispatcher = StandardTestDispatcher(testScheduler)
        Dispatchers.setMain(dispatcher)
        getPhotosUseCase = GetPhotosUseCase(repository)
    }

    @Test
    fun `executing GetSearchResultsUseCase, when the api returns search results list it will throw an exception and the useCase will return empty list`() =
        runTest {
            //Arrange
            whenever(repository.getPhotos("Color121212", 1)).thenReturn(emptyFlow())

            //Action
            val result = getPhotosUseCase("Color121212", 1)

            advanceUntilIdle()

            //Assert
            Assert.assertEquals(result, emptyFlow<PhotoEntity>())
        }
}