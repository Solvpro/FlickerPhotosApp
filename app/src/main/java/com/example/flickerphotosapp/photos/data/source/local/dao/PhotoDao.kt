package com.example.flickerphotosapp.photos.data.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flickerphotosapp.photos.domain.entity.PhotoEntity

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(photos: List<PhotoEntity>)

    @Query("SELECT * FROM photos ORDER BY title ASC")
    fun getAllPhotos(): PagingSource<Int, PhotoEntity>

    @Query("DELETE FROM photos")
    suspend fun deleteAll()

}