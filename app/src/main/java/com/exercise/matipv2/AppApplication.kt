package com.exercise.matipv2

import android.app.Application
import com.exercise.matipv2.data.AppDatabase

class AppApplication : Application() {
    val db: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }
}