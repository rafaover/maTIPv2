package com.exercise.matipv2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exercise.matipv2.data.model.Tip
import kotlinx.coroutines.flow.Flow

@Dao
interface TipDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTip(tip: Tip)

    @Delete
    suspend fun deleteTip(tip: Tip)

    @Query("SELECT * FROM tips")
    fun getAllTips(): Flow<List<Tip>>

    // TODO("Comment this before going to production")
    @Query("DELETE FROM tips")
    suspend fun deleteAllTips()
    //
}