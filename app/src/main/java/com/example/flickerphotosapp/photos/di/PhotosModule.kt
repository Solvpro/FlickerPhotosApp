package com.example.flickerphotosapp.photos.di


import androidx.paging.ExperimentalPagingApi
import com.example.flickerphotosapp.photos.data.repository.PhotosRepositoryImpl
import com.example.flickerphotosapp.photos.data.source.remote.PhotosApi
import com.example.flickerphotosapp.photos.domain.repository.PhotosRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
@ExperimentalPagingApi
abstract class PhotosModule {

    @Binds
    abstract fun bindPhotosRepository(imp: PhotosRepositoryImpl): PhotosRepository

    companion object {
        @Provides
        fun providePhotosApi(retrofit: Retrofit): PhotosApi = retrofit.create()

    }
}