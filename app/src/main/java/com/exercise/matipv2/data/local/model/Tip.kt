package com.exercise.matipv2.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tips")
data class Tip(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    /**
     * The amount of the [Tip] given after calculations. In code
     * is known as FinalTip.
     */
    @ColumnInfo(name = "tip_amount")
    val tipAmount: String,
    @ColumnInfo(name = "tip_percent")
    val tipPercent: String,

    /**
     * The [Event] id that the [Tip] is associated with.
     */
    @ColumnInfo(name = "event_id")
    var eventId: Int
)