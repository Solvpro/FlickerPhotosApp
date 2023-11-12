package com.example.flickerphotosapp.network

object NoInternetException : Throwable()

object NoSearchResult : Throwable()

data class ServerError(val errorMessage: String) : Throwable()