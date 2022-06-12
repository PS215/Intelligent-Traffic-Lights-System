package com.ps215.capstoneITLS.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "density_table")
data class Traffic (
    @PrimaryKey
    val id: String,
    val density: Int = 0
)