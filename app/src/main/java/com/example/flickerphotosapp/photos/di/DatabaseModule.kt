package com.example.flickerphotosapp.photos.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flickerphotosapp.photos.data.source.local.AppDatabase
import com.example.flickerphotosapp.photos.data.source.local.dao.PhotoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, "photos_database"
        ).fallbackToDestructiveMigration()
            .setQueryExecutor(Executors.newSingleThreadExecutor())
            .setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING)
            .build()
    }

    @Provides
    fun providePhotoDao(database: AppDatabase): PhotoDao {
        return database.photoDao()
    }

}