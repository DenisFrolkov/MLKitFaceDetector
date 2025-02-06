package com.denis.mlkitfacedetector

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoreApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}