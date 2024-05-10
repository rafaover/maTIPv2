package com.exercise.matipv2.data

import com.exercise.matipv2.data.model.Event
import com.exercise.matipv2.data.model.EventWithTips
import com.exercise.matipv2.data.model.Tip
import kotlinx.coroutines.flow.Flow

interface MatipRepository {
    // Tip Methods
    suspend fun insertTip(tip: Tip)
    suspend fun deleteTip(tip: Tip)

    // TODO("Comment this before going to production")
    suspend fun deleteAllTips()
    //

    fun getAllTips(): Flow<List<Tip>>

    // Event Methods
    suspend fun insertEvent(event: Event)
    suspend fun deleteEvent(event: Event)

    // TODO("Comment this before going to production")
    suspend fun deleteAllEvents()
    //

    fun getAllEvents(): Flow<List<Event>>
    fun getEventWithTips(eventId: Int): Flow<EventWithTips>
}