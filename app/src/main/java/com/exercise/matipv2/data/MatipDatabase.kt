package com.exercise.matipv2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.exercise.matipv2.data.model.Event
import com.exercise.matipv2.data.model.Tip

@Database(entities = [Event::class, Tip::class], version = 1)
abstract class MatipDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
    abstract fun tipDao(): TipDao
}