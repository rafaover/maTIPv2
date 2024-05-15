package com.exercise.matipv2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.exercise.matipv2.data.model.Event
import com.exercise.matipv2.data.model.EventWithTips
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(event: Event)

    @Delete
    suspend fun delete(event: Event)

    @Query("SELECT * FROM events")
    fun getAllEvents(): Flow<List<Event>>

    @Transaction
    @Query("SELECT * FROM events")
    fun getEventWithTips(): Flow<List<EventWithTips>>
}