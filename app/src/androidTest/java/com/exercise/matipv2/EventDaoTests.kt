package com.exercise.matipv2

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.MediumTest
import com.exercise.matipv2.data.MatipDatabase
import com.exercise.matipv2.data.dao.EventDao
import com.exercise.matipv2.data.dao.TipDao
import com.exercise.matipv2.data.model.Event
import com.exercise.matipv2.data.model.Tip
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

@MediumTest
class EventDaoTests {
//    @get: Rule
//    val dispatcherRule = TestDispatcherRule()

    private lateinit var db: MatipDatabase
    private lateinit var eventDao: EventDao
    private lateinit var tipDao: TipDao

    @Before
    @Throws(IOException::class)
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room
            .inMemoryDatabaseBuilder(context, MatipDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        eventDao = db.eventDao()
        tipDao = db.tipDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    /* Test case to insert and read event */
    @Test
    @Throws(Exception::class)
    fun insertEvent_GetEvent() = runTest {
        val event = Event(id = 1, name = "EventTest")
        eventDao.insertEvent(event)
        val byEventName = eventDao.getEventByName("EventTest").first()
        assertEquals(byEventName, event)
    }

    /* Test case to insert and delete event */
    @Test
    @Throws(Exception::class)
    fun insertEvent_DeleteEvent() = runTest {
        val event = Event(id = 1, name = "EventTest")
        eventDao.insertEvent(event)
        eventDao.deleteEvent(event)
        val byEventName = eventDao.getEventByName("EventTest").first()
        assertEquals(byEventName, null)
    }

    /* Test case to get all Events and Read all */
    @Test
    @Throws(Exception::class)
    fun insertEvents_getAllEvents() = runTest {
        for (i in 1..5) {
            val event = Event(id = i, name = "EventTest$i")
            eventDao.insertEvent(event)
        }
        val allEvents = eventDao.getAllEvents().first()
        assertEquals(allEvents.size, 5)
        assertEquals(allEvents[0].name, "EventTest1")
        assertEquals(allEvents[1].name, "EventTest2")
    }

    @Test
    @Throws(Exception::class)
    fun updateEvent_getEvent() = runTest {
        val event = Event(id = 1, name = "EventTest")
        eventDao.insertEvent(event)
        val updatedEvent = Event(id = 1, name = "EventTestUpdated")
        eventDao.updateEvent(updatedEvent)
        val byEventName = eventDao.getEventByName("EventTestUpdated").first()
        assertEquals(byEventName, updatedEvent)
    }

    @Test
    @Throws(Exception::class)
    fun getAllEventsWithTips() = runBlocking {
        // Add 10 events
        for (i in 1..10) {
            val event = Event(id = i, name = "EventTest$i")
            eventDao.insertEvent(event)
        }
        // Add 3 tips to EventTest1
        for (i in 1..3) {
            val tip = Tip(id = i, tipAmount = "${i*250}", tipPercent = "${i*2}", eventId = 1)
            tipDao.insertTip(tip)
        }
        // Add 3 tips to EventTest2
        for (i in 4..6) {
            val tip = Tip(id = i, tipAmount = "${i*250}", tipPercent = "${i*3}", eventId = 2)
            tipDao.insertTip(tip)
        }
        val getAllEvents = eventDao.getAllEvents().first() // 10
        val eventsWithTips = eventDao.getAllEventsWithTips().first() // 2
        assertEquals(getAllEvents.size, 10)
        assertEquals(eventsWithTips.size, 2)
    }
}
