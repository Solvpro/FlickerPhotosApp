package com.example.flickerphotosapp.application

import android.app.Application
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import dagger.hilt.android.HiltAndroidApp
import com.example.flickerphotosapp.BuildConfig


@HiltAndroidApp
class FlickerPhotosApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCenter.start(
            this,
            BuildConfig.APP_SECRET,
            Analytics::class.java,
            Crashes::class.java
        )
    }
}