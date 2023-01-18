package com.steve_md.youtubeapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


class YouTube : Application() {
    override fun onCreate() {
        super.onCreate()
        // Timber for Logging
        Timber.plant(Timber.DebugTree())
    }
}