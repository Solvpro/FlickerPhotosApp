package com.example.flickerphotosapp.photos.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.flickerphotosapp.photos.data.paging.PhotoRemoteMediator
import com.example.flickerphotosapp.photos.data.source.local.AppDatabase
import com.example.flickerphotosapp.photos.data.source.remote.PhotosApi
import com.example.flickerphotosapp.photos.domain.entity.PhotoEntity
import com.example.flickerphotosapp.photos.domain.repository.PhotosRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class PhotosRepositoryImpl @Inject constructor(
    private val photosApi: PhotosApi,
    private val appDatabase: AppDatabase,
) : PhotosRepository {

    override fun getPhotos(text: String, page: Int): Flow<PagingData<PhotoEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = PhotoRemoteMediator(appDatabase, photosApi, text),
            pagingSourceFactory = { appDatabase.photoDao().getAllPhotos() }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

}