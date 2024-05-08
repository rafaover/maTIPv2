package com.exercise.matipv2.data

import com.exercise.matipv2.data.model.Event
import com.exercise.matipv2.data.model.Tip

class OfflineMatipRepository(
    private val tipDao: TipDao,
    private val eventDao: EventDao
) {
    // Tip Methods
    fun insertTip(tip: Tip) = tipDao.insertTip(tip)
    fun deleteTip(tip: Tip) = tipDao.deleteTip(tip)
    fun getAllTips() = tipDao.getAllTips()
    fun getTipsByEventId(eventId: Int) = tipDao.getTipsByEventId(eventId)

    // Event Methods
    fun insertEvent(event: Event) = eventDao.insert(event)
    fun deleteEvent(event: Event) = eventDao.delete(event)
    fun getAllEvents() = eventDao.getAllEvents()
}