package com.example.flickerphotosapp.photos.data.model

import com.google.gson.annotations.SerializedName

data class PhotosModel(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("pages") var totalPages: Int? = null,
    @SerializedName("perpage") var perPage: Int? = null,
    @SerializedName("total") var totalItems: Int? = null,
    @SerializedName("photo") var photos: List<PhotoModel>,
)