package com.exercise.matipv2.data.model

import androidx.test.filters.SmallTest
import com.exercise.matipv2.data.local.model.Event
import com.exercise.matipv2.data.local.model.EventWithTips
import com.exercise.matipv2.data.local.model.Tip
import org.junit.Assert.*
import org.junit.Test

@SmallTest
class EventWithTipsTest {

    @Test
    fun testEventWithTips_correctValues() {
        val event = Event(1, "Event 1")
        val tip1 = Tip(1, "2500", "10", 1)
        val tip2 = Tip(2, "3500", "10", 1)
        val eventWithTips = EventWithTips(event, listOf(tip1, tip2))

        assertEquals(event, eventWithTips.event)
        assertEquals(listOf(tip1, tip2), eventWithTips.tips)
    }
}