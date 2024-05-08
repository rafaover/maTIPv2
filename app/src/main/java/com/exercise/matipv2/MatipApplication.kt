package com.exercise.matipv2

import android.app.Application
import com.exercise.matipv2.data.MatipDatabase

class MatipApplication : Application() {
    val db: MatipDatabase by lazy {
        MatipDatabase.getDatabase(this)
    }
}