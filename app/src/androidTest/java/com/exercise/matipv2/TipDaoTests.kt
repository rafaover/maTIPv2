package com.exercise.matipv2

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.MediumTest
import com.exercise.matipv2.data.MatipDatabase
import com.exercise.matipv2.data.dao.EventDao
import com.exercise.matipv2.data.dao.TipDao
import com.exercise.matipv2.data.model.Tip
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

@MediumTest
class TipDaoTests {
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

    @Test
    @Throws(Exception::class)
    fun insertTip_getTip() = runTest {
        val tip = Tip(id = 1, tipAmount = "100", tipPercent = "10", eventId = null)
        tipDao.insertTip(tip)
        val getTip = tipDao.getAllTips().first()[0]
        assertEquals(getTip.tipAmount, "100")
    }

    @Test
    @Throws(Exception::class)
    fun deleteTip() = runTest {
        val tip = Tip(id = 1, tipAmount = "100", tipPercent = "10", eventId = null)
        tipDao.insertTip(tip)
        tipDao.deleteTip(tip)
        val getTip = tipDao.getAllTips().first()
        assertEquals(getTip.size, 0)
    }

    @Test
    @Throws(Exception::class)
    fun updateTip() = runTest {
        val tip = Tip(id = 1, tipAmount = "100", tipPercent = "10", eventId = null)
        tipDao.insertTip(tip)
        val tip1 = Tip(id = 1, tipAmount = "200", tipPercent = "10", eventId = null)
        tipDao.updateTip(tip1)
        val updatedTip = tipDao.getAllTips().first()[0]
        assertEquals(updatedTip.tipAmount, "200")
    }

    @Test
    @Throws(Exception::class)
    fun getAllTips() = runTest {
        val tip1 = Tip(id = 1, tipAmount = "100", tipPercent = "10", eventId = null)
        val tip2 = Tip(id = 2, tipAmount = "200", tipPercent = "20", eventId = null)
        tipDao.insertTip(tip1)
        tipDao.insertTip(tip2)
        val getTips = tipDao.getAllTips().first()
        assertEquals(getTips.size, 2)
    }

    @Test
    @Throws(Exception::class)
    fun getLastTipSaved() = runTest {
        val tip1 = Tip(id = 1, tipAmount = "100", tipPercent = "10", eventId = null)
        val tip2 = Tip(id = 2, tipAmount = "200", tipPercent = "20", eventId = null)
        tipDao.insertTip(tip1)
        tipDao.insertTip(tip2)
        val lastTip = tipDao.getLastTipSaved()
        assertEquals(lastTip.id, 2)
    }

    @Test
    @Throws(Exception::class)
    fun getAllTipsFromEvents() = runTest {
        val tip1 = Tip(id = 1, tipAmount = "100", tipPercent = "10", eventId = 1)
        val tip2 = Tip(id = 2, tipAmount = "200", tipPercent = "20", eventId = 1)
        val tip3 = Tip(id = 3, tipAmount = "300", tipPercent = "30", eventId = 2)
        tipDao.insertTip(tip1)
        tipDao.insertTip(tip2)
        tipDao.insertTip(tip3)
        val getTips = tipDao.getAllTipsFromEvent(1).first()
        assertEquals(getTips.size, 2)
    }


}
