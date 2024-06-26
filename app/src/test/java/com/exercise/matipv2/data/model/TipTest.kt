package com.exercise.matipv2.data.model

import androidx.test.filters.SmallTest
import com.exercise.matipv2.data.local.model.Tip
import org.junit.Assert.*
import org.junit.Test

@SmallTest
class TipTest {

    @Test
    fun tipModel_correctlyStoresData() {
        // Given
        val expectedId = 1
        val expectedTipAmount = "10"
        val expectedTipPercent = "10"
        val expectedEventId = 1

        // When
        val tip = Tip(id = expectedId, tipAmount = expectedTipAmount, tipPercent = expectedTipPercent, eventId = expectedEventId)

        // Then
        assertEquals(expectedId, tip.id)
        assertEquals(expectedTipAmount, tip.tipAmount)
        assertEquals(expectedTipPercent, tip.tipPercent)
        assertEquals(expectedEventId, tip.eventId)
    }

}