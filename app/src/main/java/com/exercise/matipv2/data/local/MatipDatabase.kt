package com.exercise.matipv2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.exercise.matipv2.data.local.dao.EventDao
import com.exercise.matipv2.data.local.dao.TipDao
import com.exercise.matipv2.data.local.model.Event
import com.exercise.matipv2.data.local.model.Tip

@Database(entities = [Event::class, Tip::class], version = 3)
abstract class MatipDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
    abstract fun tipDao(): TipDao
}