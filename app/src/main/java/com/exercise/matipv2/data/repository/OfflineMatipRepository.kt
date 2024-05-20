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
    override suspend fun addTipToEvent(tipId: Int, eventId: Int) {
        tipDao.addTipToEvent(tipId, eventId)
    }
    override suspend fun getLastTipSaved(): Tip {
        return tipDao.getLastTipSaved()
    }
    override fun getAllTipsFromEvent(eventId: Int): Flow<List<Tip>> {
        return tipDao.getAllTipsFromEvent(eventId)
    }

    // Event Methods
    override suspend fun insertEvent(event: Event) = eventDao.insert(event)
    override suspend fun deleteEvent(event: Event) = eventDao.delete(event)
    override fun getAllEvents() = eventDao.getAllEvents()
    override fun getEventWithTips(): Flow<List<EventWithTips>> {
        return eventDao.getEventWithTips()
    }
}