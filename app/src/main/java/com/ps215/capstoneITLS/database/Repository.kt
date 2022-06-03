package com.ps215.capstoneITLS.database

import androidx.lifecycle.LiveData

class Repository(private val trafficDao: TrafficDao) {
    fun getAllTraffic(): LiveData<List<Traffic>> = trafficDao.getAllTraffic()

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(trafficDao: TrafficDao): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(trafficDao).apply { instance = this }
            }
    }
}