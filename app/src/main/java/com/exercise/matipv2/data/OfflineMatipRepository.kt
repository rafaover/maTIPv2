package com.exercise.matipv2.data

import com.exercise.matipv2.data.model.Event
import com.exercise.matipv2.data.model.Tip

class OfflineMatipRepository(
    private val tipDao: TipDao,
    private val eventDao: EventDao
) : MatipRepository {

    // Tip Methods
    override suspend fun insertTip(tip: Tip) = tipDao.insertTip(tip)
    override suspend fun deleteTip(tip: Tip) = tipDao.deleteTip(tip)
    override fun getAllTips() = tipDao.getAllTips()
    override fun getTipsByEventId(eventId: Int) = tipDao.getTipsByEventId(eventId)

    // Event Methods
    override suspend fun insertEvent(event: Event) = eventDao.insert(event)
    override suspend fun deleteEvent(event: Event) = eventDao.delete(event)
    override fun getAllEvents() = eventDao.getAllEvents()
}