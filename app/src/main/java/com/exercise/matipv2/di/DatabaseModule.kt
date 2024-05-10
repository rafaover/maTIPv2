package com.exercise.matipv2.di

import android.content.Context
import androidx.room.Room
import com.exercise.matipv2.data.MatipDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context): MatipDatabase {
        return Room.databaseBuilder(
            context,
            MatipDatabase::class.java,
            "matip_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun getEventDao(database: MatipDatabase) = database.eventDao()
    @Provides
    fun getTipDao(database: MatipDatabase) = database.tipDao()
}