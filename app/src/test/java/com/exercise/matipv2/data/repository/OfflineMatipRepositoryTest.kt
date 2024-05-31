package com.exercise.matipv2.data.repository

import androidx.test.filters.MediumTest
import com.exercise.matipv2.data.local.dao.EventDao
import com.exercise.matipv2.data.local.dao.TipDao
import com.exercise.matipv2.data.local.model.Event
import com.exercise.matipv2.data.local.model.EventWithTips
import com.exercise.matipv2.data.local.model.Tip
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@MediumTest
@RunWith(MockitoJUnitRunner::class)
class OfflineMatipRepositoryTest {

    @Mock
    private lateinit var repository: OfflineMatipRepository
    private val mockTipDao = mock<TipDao>()
    private val mockEventDao = mock<EventDao>()


    @Before
    fun setUp() {
        repository = OfflineMatipRepository(mockTipDao, mockEventDao)
    }

    @Test
    fun insertTip() = runBlocking {
        val tip = Tip(1, "100", "10", null)
        repository.insertTip(tip)
        verify(mockTipDao).insertTip(tip)
    }

    @Test
    fun deleteTip() = runBlocking {
        val tip = Tip(1, "100", "10", null)
        repository.deleteTip(tip)
        verify(mockTipDao).deleteTip(tip)
    }

    @Test
    fun updateTip() = runBlocking {
        val tip1 = Tip(1, "100", "10", null)
        repository.insertTip(tip1)
        val tip2 = Tip(1, "200", "20", null)
        repository.updateTip(tip2)
        verify(mockTipDao).updateTip(tip2)
    }

    @Test
    fun getAllTips() = runBlocking {
        val tip1 = Tip(1, "100", "10", null)
        val tip2 = Tip(2, "200", "20", null)
        `when`(mockTipDao.getAllTips()).thenReturn(flowOf(listOf(tip1, tip2)))
        val allTips = repository.getAllTips().first()
        verify(mockTipDao).getAllTips()
        assertEquals(listOf(tip1, tip2), allTips)
    }

    @Test
    fun getLastTipSaved() = runBlocking {
        val tip = Tip(1, "100", "10", null)
        `when`(mockTipDao.getLastTipSaved()).thenReturn(tip)
        val lastTip = repository.getLastTipSaved()
        verify(mockTipDao).getLastTipSaved()
        assertEquals(tip, lastTip)
    }

    @Test
    fun getAllTipsFromEvent() = runBlocking {
        val tip1 = Tip(1, "100", "10", 1)
        val tip2 = Tip(2, "200", "20", 1)
        `when`(mockTipDao.getAllTipsFromEvent(1)).thenReturn(flowOf(listOf(tip1, tip2)))
        val allTips = repository.getAllTipsFromEvent(1).first()
        verify(mockTipDao).getAllTipsFromEvent(1)
        assertEquals(listOf(tip1, tip2), allTips)
    }

    @Test
    fun insertEvent() = runBlocking {
        val event = Event(1, "EventTest1")
        repository.insertEvent(event)
        verify(mockEventDao).insertEvent(event)
    }

    @Test
    fun deleteEvent() = runBlocking {
        val event = Event(1, "EventTest1")
        repository.deleteEvent(event)
        verify(mockEventDao).deleteEvent(event)
    }

    @Test
    fun updateEvent() = runBlocking {
        val event1 = Event(1, "EventTest1")
        repository.insertEvent(event1)
        val event2 = Event(1, "EventTest2")
        repository.updateEvent(event2)
        verify(mockEventDao).updateEvent(event2)
    }

    @Test
    fun getAllEvents() = runBlocking {
        val event1 = Event(1, "EventTest1")
        val event2 = Event(2, "EventTest2")
        `when`(mockEventDao.getAllEvents()).thenReturn(flowOf(listOf(event1, event2)))
        val allEvents = repository.getAllEvents().first()
        verify(mockEventDao).getAllEvents()
        assertEquals(listOf(event1, event2), allEvents)
    }

    @Test
    fun getAllEventsWithTips() = runBlocking {
        val event1 = Event(1, "EventTest1")
        val event2 = Event(2, "EventTest2")
        val tip1 = Tip(1, "100", "10", 1)
        val tip2 = Tip(2, "200", "20", 2)
        val eventWithTips1 = EventWithTips(event1, listOf(tip1))
        val eventWithTips2 = EventWithTips(event2, listOf(tip2))
        `when`(mockEventDao.getAllEventsWithTips())
            .thenReturn(flowOf(listOf(eventWithTips1, eventWithTips2)))

        val allEventsWithTips = repository.getAllEventsWithTips().first()
        verify(mockEventDao).getAllEventsWithTips()
        assertEquals(listOf(eventWithTips1, eventWithTips2), allEventsWithTips)
    }

    @Test
    fun getEventByName() = runBlocking {
        val event = Event(1, "EventTest1")
        `when`(mockEventDao.getEventByName("EventTest1")).thenReturn(flowOf(event))
        val eventByName = repository.getEventByName("EventTest1").first()
        verify(mockEventDao).getEventByName("EventTest1")
        assertEquals(event, eventByName)
    }
}