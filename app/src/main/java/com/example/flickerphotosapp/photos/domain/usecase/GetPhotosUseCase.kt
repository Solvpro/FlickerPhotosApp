package com.example.flickerphotosapp.photos.domain.usecase

import androidx.paging.PagingData
import com.example.flickerphotosapp.photos.domain.entity.PhotoEntity
import com.example.flickerphotosapp.photos.domain.repository.PhotosRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val repository: PhotosRepository) {
    operator fun invoke(query: String, page: Int): Flow<PagingData<PhotoEntity>> {
        return repository.getPhotos(query, page)
    }
}