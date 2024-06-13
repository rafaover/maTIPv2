package com.exercise.matipv2.data.local.model

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Collects an [Event] with all their tips.
 * Uses the foreign Key on [Tip].
*/
data class EventWithTips(
    @Embedded val event: Event,
    @Relation(
        parentColumn = "id",
        entityColumn = "event_id"
    )
    val tips: List<Tip>
)
