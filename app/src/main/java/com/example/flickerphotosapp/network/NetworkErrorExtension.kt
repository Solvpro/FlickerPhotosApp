package com.example.flickerphotosapp.network

import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.mapConnectionException() = when (this) { // mapConnectionException
    is ConnectException, is SocketTimeoutException, is UnknownHostException, is IOException -> NoInternetException
    else -> this
}

fun Throwable.mapSearchErrors() = when (this) { // mapSearchErrors
    is ConnectException, is SocketTimeoutException, is UnknownHostException, is IOException -> NoInternetException
    is HttpException -> {
        if (code() == 404) NoSearchResult else this
    }
    else -> this
}