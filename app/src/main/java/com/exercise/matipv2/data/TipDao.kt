package com.exercise.matipv2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exercise.matipv2.data.model.Tip

@Dao
interface TipDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTip(tip: Tip)

    @Delete
    fun deleteTip(tip: Tip)

    @Query("SELECT * FROM tips")
    fun getAllTips(): List<Tip>

    @Query("SELECT * FROM tips WHERE tip_event = :eventId")
    fun getTipsByEventId(eventId: Int): List<Tip>
}