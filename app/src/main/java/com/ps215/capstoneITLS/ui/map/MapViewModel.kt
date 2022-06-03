package com.ps215.capstoneITLS.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ps215.capstoneITLS.database.Repository
import com.ps215.capstoneITLS.database.Traffic

class MapViewModel(private val trafficRepository: Repository) : ViewModel() {
    fun getAllTraffic(): LiveData<List<Traffic>> = trafficRepository.getAllTraffic()
}