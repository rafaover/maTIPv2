package com.exercise.matipv2.data

import com.exercise.matipv2.data.model.Event
import com.exercise.matipv2.data.model.Tip
import kotlinx.coroutines.flow.Flow

interface MatipRepository {
    // Tip Methods
    suspend fun insertTip(tip: Tip)
    suspend fun deleteTip(tip: Tip)
    fun getAllTips(): Flow<List<Tip>>
    fun getTipsByEventId(eventId: Int): Flow<List<Tip>>

    // Event Methods
    suspend fun insertEvent(event: Event)
    suspend fun deleteEvent(event: Event)
    fun getAllEvents(): Flow<List<Event>>
}