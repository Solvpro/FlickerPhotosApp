package com.example.flickerphotosapp.photos.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class PhotoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val photoId: String,
    val title: String,
    val imageUrl: String
)
