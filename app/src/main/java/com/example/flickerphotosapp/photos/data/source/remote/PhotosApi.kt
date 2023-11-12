package com.example.flickerphotosapp.photos.data.source.remote

import com.example.flickerphotosapp.BuildConfig
import com.example.flickerphotosapp.photos.data.model.PhotosResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {

    @GET("/services/rest")
    suspend fun searchPhotos(
        @Query("method") method: String = "flickr.photos.search",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1,
        @Query("text") text: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): PhotosResponseModel

}