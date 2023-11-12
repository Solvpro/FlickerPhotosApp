package com.example.flickerphotosapp.photos.data.model

import com.google.gson.annotations.SerializedName

data class PhotosResponseModel(
    @SerializedName("photos")
    val photos: PhotosModel,
    @SerializedName("stat")
    val status: String? = null
)