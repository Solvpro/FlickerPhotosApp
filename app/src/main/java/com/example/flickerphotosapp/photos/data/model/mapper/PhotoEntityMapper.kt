package com.example.flickerphotosapp.photos.data.model.mapper

import com.example.flickerphotosapp.photos.data.model.PhotoModel
import com.example.flickerphotosapp.photos.domain.entity.PhotoEntity


fun PhotoModel.toEntity() = PhotoEntity(
    photoId = id,
    title = title,
    imageUrl = "https://farm${farm}.staticflickr.com/${server}/${id}_${secret}.jpg",
)