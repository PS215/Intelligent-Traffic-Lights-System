package com.ps215.capstoneITLS.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ps215.capstoneITLS.data.Repository
import com.ps215.capstoneITLS.data.model.TrafficList

class MapViewModel(private val trafficRepository: Repository) : ViewModel() {
    fun setTraffic(){
        trafficRepository.setTraffic()
    }

    fun getTraffic(): LiveData<ArrayList<TrafficList>> {
        return trafficRepository.getTraffic()
    }
}