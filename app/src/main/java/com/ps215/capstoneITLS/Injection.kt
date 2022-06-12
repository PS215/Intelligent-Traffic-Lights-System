package com.ps215.capstoneITLS

import android.content.Context
import com.ps215.capstoneITLS.data.Repository
import com.ps215.capstoneITLS.data.database.TrafficDatabase

object Injection {

    fun provideRepository(context: Context): Repository {
        val database = TrafficDatabase.getDatabase(context)
        return Repository.getInstance(database.trafficDao())
    }

}