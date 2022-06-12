package com.ps215.capstoneITLS.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Traffic::class], version = 1, exportSchema = false)
abstract class TrafficDatabase : RoomDatabase() {

    abstract fun trafficDao(): TrafficDao

    companion object {
        @Volatile
        private var INSTANCE: TrafficDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): TrafficDatabase {
            if (INSTANCE == null) {
                synchronized(TrafficDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        TrafficDatabase::class.java, "traffic_database")
                        .fallbackToDestructiveMigration()
                        .createFromAsset("database/trafficDensity.db")
                        .build()
                }
            }
            return INSTANCE as TrafficDatabase
        }

    }
}