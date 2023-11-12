package com.example.flickerphotosapp.photos.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.flickerphotosapp.di.IODispatcher
import com.example.flickerphotosapp.photos.data.model.mapper.toEntity
import com.example.flickerphotosapp.photos.data.source.local.AppDatabase
import com.example.flickerphotosapp.photos.data.source.remote.PhotosApi
import com.example.flickerphotosapp.photos.domain.entity.PhotoEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PhotoRemoteMediator(
    private val appDatabase: AppDatabase,
    private val photosApi: PhotosApi,
    private val query: String,
    @IODispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : RemoteMediator<Int, PhotoEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PhotoEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val searchPhotos = withContext(dispatcher) {
                photosApi.searchPhotos(
                    page = loadKey,
                    text = query,
                    perPage = state.config.pageSize
                )
            }

            val photos = searchPhotos.photos.photos

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    appDatabase.photoDao().deleteAll()
                }
                val userFeedEntities = photos.map { it.toEntity() }
                appDatabase.photoDao().addAll(userFeedEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = photos.isNotEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}
