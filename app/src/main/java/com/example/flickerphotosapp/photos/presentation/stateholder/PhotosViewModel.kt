package com.example.flickerphotosapp.photos.presentation.stateholder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.flickerphotosapp.photos.domain.entity.PhotoEntity
import com.example.flickerphotosapp.photos.domain.usecase.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    private val _photos = MutableStateFlow<PagingData<PhotoEntity>>(PagingData.empty())
    val photos: StateFlow<PagingData<PhotoEntity>> = _photos

    fun getPhotos(query: String, page: Int) {
        viewModelScope.launch {
            getPhotosUseCase.invoke(query, page)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _photos.value = pagingData
                }
        }
    }
}