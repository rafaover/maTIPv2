package com.exercise.matipv2.data.repository

import com.exercise.matipv2.data.dao.EventDao
import com.exercise.matipv2.data.dao.TipDao
import com.exercise.matipv2.data.model.Event
import com.exercise.matipv2.data.model.EventWithTips
import com.exercise.matipv2.data.model.Tip
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineMatipRepository @Inject constructor (
    private val tipDao: TipDao,
    private val eventDao: EventDao
) : MatipRepository {

    // Tip Methods
    override suspend fun insertTip(tip: Tip) = tipDao.insertTip(tip)
    override suspend fun deleteTip(tip: Tip) = tipDao.deleteTip(tip)
    override suspend fun updateTip(tip: Tip) = tipDao.updateTip(tip)
    override fun getAllTips() = tipDao.getAllTips()
    override suspend fun getLastTipSaved(): Tip {
        return tipDao.getLastTipSaved()
    }
    override fun getAllTipsFromEvent(eventId: Int): Flow<List<Tip>> {
        return tipDao.getAllTipsFromEvent(eventId)
    }

    // Event Methods
    override suspend fun insertEvent(event: Event) = eventDao.insertEvent(event)
    override suspend fun deleteEvent(event: Event) = eventDao.deleteEvent(event)
    override suspend fun updateEvent(event: Event) = eventDao.updateEvent(event)
    override fun getAllEvents() = eventDao.getAllEvents()
    override fun getAllEventsWithTips(): Flow<List<EventWithTips>> {
        return eventDao.getAllEventsWithTips()
    }
    override fun getEventByName(eventName: String): Flow<Event> {
        return eventDao.getEventByName(eventName)
    }
    override fun getEventById(eventId: Int): Flow<Event> {
        return eventDao.getEventById(eventId)
    }
}