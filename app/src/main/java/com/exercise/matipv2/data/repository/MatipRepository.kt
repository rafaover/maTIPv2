package com.exercise.matipv2.data.repository

import com.exercise.matipv2.data.model.Event
import com.exercise.matipv2.data.model.EventWithTips
import com.exercise.matipv2.data.model.Tip
import kotlinx.coroutines.flow.Flow

interface MatipRepository {
    /* Tip Methods */
    suspend fun insertTip(tip: Tip)
    suspend fun deleteTip(tip: Tip)
    suspend fun updateTip(tip: Tip)
    fun getAllTips(): Flow<List<Tip>>
    suspend fun getLastTipSaved(): Tip
    fun getAllTipsFromEvent(eventId: Int): Flow<List<Tip>>

    /* Event Methods */
    suspend fun insertEvent(event: Event)
    suspend fun deleteEvent(event: Event)
    suspend fun updateEvent(event: Event)
    fun getAllEvents(): Flow<List<Event>>
    fun getEventByName(eventName: String): Flow<Event>
    fun getEventById(eventId: Int): Flow<Event>
    fun getAllEventsWithTips(): Flow<List<EventWithTips>>
}