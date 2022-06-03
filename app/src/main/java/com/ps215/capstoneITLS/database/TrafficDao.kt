package com.ps215.capstoneITLS.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TrafficDao {
    @Query("SELECT * from traffic")
    fun getAllTraffic(): LiveData<List<Traffic>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTraffic(traffic: List<Traffic>)
}