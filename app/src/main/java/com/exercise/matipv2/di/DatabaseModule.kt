package com.exercise.matipv2.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.exercise.matipv2.data.EventDao
import com.exercise.matipv2.data.MatipDatabase
import com.exercise.matipv2.data.MatipRepository
import com.exercise.matipv2.data.OfflineMatipRepository
import com.exercise.matipv2.data.TipDao
import com.exercise.matipv2.data.model.Event
import com.exercise.matipv2.data.model.Tip
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context): MatipDatabase {
        lateinit var database: MatipDatabase
        database = Room.databaseBuilder(context, MatipDatabase::class.java, "app_database")
                .fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            prePopulateDb(
                                database.eventDao(),
                                database.tipDao()
                            )
                        }
                    }
                })
                .build()
        Log.d("DatabaseModule","Database has been created")
        return database
    }

    suspend fun prePopulateDb(eventDao: EventDao, tipDao: TipDao) {
        val event = Event(1, "Event 1")
        eventDao.insert(event)
        val tip1 = Tip(1, "100", "10.0", 1)
        val tip2 = Tip(2, "200", "20.0", 1)
        tipDao.insertTip(tip1)
        tipDao.insertTip(tip2)
    }

    @Provides
    fun getEventDao(database: MatipDatabase) = database.eventDao()
    @Provides
    fun getTipDao(database: MatipDatabase) = database.tipDao()
    @Provides
    fun provideMatipRepository(eventDao: EventDao, tipDao: TipDao): MatipRepository {
        return OfflineMatipRepository(tipDao, eventDao)
    }
}