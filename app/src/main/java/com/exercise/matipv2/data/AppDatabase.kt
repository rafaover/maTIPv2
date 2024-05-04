package com.exercise.matipv2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.exercise.matipv2.data.model.Event
import com.exercise.matipv2.data.model.Tip

@Database(entities = [Event::class, Tip::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
    abstract fun tipDao(): TipDao

    /**
     * 1. Singleton Pattern: The `BusScheduleDatabase` class uses the Singleton pattern to ensure
     * only one instance of the database is created throughout the application. This is achieved
     * by storing the instance in a private variable `Instance` and providing a `getDatabase`
     * method to retrieve it. This method checks if `Instance` is null, and if it is, it creates a
     * new instance of the database. This ensures that all parts of your application are interacting
     * with the same database instance.
     *
     * 2. Thread-Safety: The `@Volatile` annotation and the `synchronized` block ensure that the
     * creation is thread-safe. This means that if multiple threads call `getDatabase` at the same
     * time, only one of them will create the database, and the others will wait until it's
     * created and then use the same instance.
     *
     * 3. Pre-populated Database: The `createFromAsset` method is used to create the database from
     * an existing database file included in your app's assets. This is useful if you want to
     * distribute your app with a pre-populated database.
     *
     * 4. Migration Strategy: The `fallbackToDestructiveMigration` method is used to specify that
     * if a migration is needed (i.e., if you increase the version number of your database), but
     * no migration strategy is provided, Room should just delete all the old data and create a
     * new empty database. This is a simple way to handle migrations, but it might not be
     * suitable if you want to preserve your users' data when updating the database schema.
     *
     */
    companion object {
        @Volatile
        private var Instance: AppDatabase? = null
        private const val DATABASE_NAME = "app_database"
        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}