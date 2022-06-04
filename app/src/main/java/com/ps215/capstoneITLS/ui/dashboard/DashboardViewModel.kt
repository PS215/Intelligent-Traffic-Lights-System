package com.ps215.capstoneITLS.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ps215.capstoneITLS.database.Repository
import com.ps215.capstoneITLS.database.model.TrafficList

class DashboardViewModel(private val trafficRepository: Repository) : ViewModel() {
    fun setTraffic(){
        trafficRepository.setTraffic()
    }

    fun getTraffic(): LiveData<ArrayList<TrafficList>> {
        return trafficRepository.getTraffic()
    }
}
