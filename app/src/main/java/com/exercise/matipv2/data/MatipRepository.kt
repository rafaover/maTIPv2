package com.exercise.matipv2.data

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
    suspend fun addTipToEvent(tipId: Int, eventId: Int)
    suspend fun getLastTipSaved(): Tip

    /* Event Methods */
    suspend fun insertEvent(event: Event)
    suspend fun deleteEvent(event: Event)
    fun getAllEvents(): Flow<List<Event>>
    fun getEventWithTips(): Flow<List<EventWithTips>>
}