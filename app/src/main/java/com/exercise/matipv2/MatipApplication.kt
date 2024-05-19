package com.exercise.matipv2

import android.app.Application
import com.exercise.matipv2.data.NavBarItems
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MatipApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        NavBarItems.values
    }
}