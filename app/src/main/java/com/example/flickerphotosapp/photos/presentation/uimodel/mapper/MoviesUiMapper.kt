package com.example.flickerphotosapp.photos.presentation.uimodel.mapper

import com.example.flickerphotosapp.photos.domain.entity.PhotoEntity
import com.example.flickerphotosapp.photos.presentation.uimodel.PhotoUiModel

fun PhotoEntity.toUiModel() =
    PhotoUiModel(id = id, title = title, imageUrl = imageUrl)