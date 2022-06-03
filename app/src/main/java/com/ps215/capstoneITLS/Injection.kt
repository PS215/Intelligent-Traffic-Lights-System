package com.ps215.capstoneITLS

import android.content.Context
import com.ps215.capstoneITLS.database.Repository
import com.ps215.capstoneITLS.database.TrafficDatabase

object Injection {
    fun provideRepository(context: Context): Repository {
        val database = TrafficDatabase.getDatabase(context)
        return Repository.getInstance(database.trafficDao())
    }
}