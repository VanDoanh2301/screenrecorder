package com.doanhtv.recordercompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ScreenRecorderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}