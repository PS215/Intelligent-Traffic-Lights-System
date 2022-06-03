package com.ps215.capstoneITLS.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "traffic")
data class Traffic (
    @PrimaryKey
    val id: Int,
    val name: String,
    val road: String,
    val lat: Double,
    val lon: Double,

    )