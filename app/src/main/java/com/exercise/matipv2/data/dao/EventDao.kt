package com.exercise.matipv2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomWarnings
import androidx.room.Transaction
import androidx.room.Update
import com.exercise.matipv2.data.model.Event
import com.exercise.matipv2.data.model.EventWithTips
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(event: Event)

    @Delete
    suspend fun delete(event: Event)

    @Update
    suspend fun update(event: Event)

    @Query("SELECT * FROM events")
    fun getAllEvents(): Flow<List<Event>>

    @Query("SELECT * FROM events WHERE event_name = :eventName")
    fun getEventByName(eventName: String): Flow<Event>

    @Transaction
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("""
        SELECT * FROM events 
        INNER JOIN tips ON events.id = tips.event_id 
        GROUP BY events.id
        """)
    fun getAllEventsWithTips(): Flow<List<EventWithTips>>
}