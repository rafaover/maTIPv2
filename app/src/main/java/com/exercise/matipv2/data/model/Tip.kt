package com.exercise.matipv2.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tips")
data class Tip(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "tip_amount")
    val tipAmount: String,
    @ColumnInfo(name = "tip_percent")
    val tipPercent: String,
    @ColumnInfo(name = "tip_date")
    val tipDate: String,
    @ColumnInfo(name = "tip_event")
    val tipEvent: Event
)