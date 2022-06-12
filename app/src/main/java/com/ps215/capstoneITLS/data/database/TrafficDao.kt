package com.ps215.capstoneITLS.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TrafficDao {
    @Query("SELECT * from density_table where id == :newid")
    fun getDensity(newid: String): LiveData<Traffic>

//    @Query("UPDATE density_table set density = :newdensity where id == :newid")
//    suspend fun updateDensity(newid: String, newdensity: Int)

    @Update
    suspend fun updateDensity(traffic: Traffic)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTraffic(traffic: List<Traffic>)
}