package com.example.flickerphotosapp.photos.presentation.navigation

interface PhotosNavigation {
    fun getFullImageDeepLink(id: Int,imageUrl:String): Pair<String, String>
}