package com.exercise.matipv2.data.model

import androidx.test.filters.SmallTest
import com.exercise.matipv2.data.local.model.Event
import org.junit.Assert.assertEquals
import org.junit.Test

@SmallTest
class EventTest {

    @Test
    fun eventModel_correctlyStoresData() {
        // Given
        val expectedId = 1
        val expectedName = "Test Event"

        // When
        val event = Event(id = expectedId, name = expectedName)

        // Then
        assertEquals(expectedId, event.id)
        assertEquals(expectedName, event.name)
    }
}