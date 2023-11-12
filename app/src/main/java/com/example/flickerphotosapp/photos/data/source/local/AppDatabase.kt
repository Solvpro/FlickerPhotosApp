package com.example.flickerphotosapp.photos.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flickerphotosapp.photos.data.source.local.dao.PhotoDao
import com.example.flickerphotosapp.photos.domain.entity.PhotoEntity

@Database(entities = [PhotoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao

}