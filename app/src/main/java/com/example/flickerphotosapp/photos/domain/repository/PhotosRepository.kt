package com.example.flickerphotosapp.photos.domain.repository

import androidx.paging.PagingData
import com.example.flickerphotosapp.photos.domain.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    fun getPhotos(text: String, page: Int): Flow<PagingData<PhotoEntity>>

}