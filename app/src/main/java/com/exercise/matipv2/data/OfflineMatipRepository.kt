package com.exercise.matipv2.data

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

    // TODO("Comment this before going to production")
    override suspend fun deleteAllTips() = tipDao.deleteAllTips()
    //

    override fun getAllTips() = tipDao.getAllTips()

    // Event Methods
    override suspend fun insertEvent(event: Event) = eventDao.insert(event)
    override suspend fun deleteEvent(event: Event) = eventDao.delete(event)

    // TODO("Comment this before going to production")
    override suspend fun deleteAllEvents() = eventDao.deleteAllEvents()
    //

    override fun getAllEvents() = eventDao.getAllEvents()
    override fun getEventWithTips(): Flow<List<EventWithTips>> {
        return eventDao.getEventWithTips()
    }
}