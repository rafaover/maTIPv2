package com.exercise.matipv2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exercise.matipv2.data.model.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(event: Event)

    @Delete
    fun delete(event: Event)

    @Query("SELECT * FROM events")
    fun getAllEvents(): Flow<List<Event>>
}

/*
Please note that Room doesn't support storing complex data types like List<Tip> directly.
Convert the List<Tip> to a supported data type (like a String or a ByteArray)
before storing it in the database, and convert it back to List<Tip> when reading it from the
database. This can be done using a TypeConverter.
 */