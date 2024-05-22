package com.exercise.matipv2

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.exercise.matipv2.data.MatipDatabase
import com.exercise.matipv2.data.dao.EventDao
import com.exercise.matipv2.data.model.Event
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@SmallTest
class RoomDbTests {
//    @get: Rule
//    val dispatcherRule = TestDispatcherRule()

    private lateinit var db: MatipDatabase
    private lateinit var eventDao: EventDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room
            .inMemoryDatabaseBuilder(context, MatipDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        eventDao = db.eventDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    /* Test case to insert and read event */
    @Test
    @Throws(Exception::class)
    fun insertEvent_ReadInList() = runTest {
        val event = Event(id = 1, name = "EventTest")
        eventDao.insert(event)
        val byEventName = eventDao.getEventByName("EventTest").first()
        assertEquals(byEventName, event)
    }

    /* Test case to insert and delete event */
    @Test
    @Throws(Exception::class)
    fun insertEvent_DeleteEvent() = runTest {
        val event = Event(id = 1, name = "EventTest")
        eventDao.insert(event)
        eventDao.delete(event)
        val byEventName = eventDao.getEventByName("EventTest").first()
        assertEquals(byEventName, null)
    }
}