package com.ps215.capstoneITLS.ui.trafficdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ps215.capstoneITLS.data.Repository
import com.ps215.capstoneITLS.data.database.Traffic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrafficDetailViewModel(private val trafficRepository: Repository) : ViewModel() {
    fun getDensity(newid: String): LiveData<Traffic> = trafficRepository.getDensity(newid)

    fun updateDensity(traffic: Traffic){
        viewModelScope.launch(Dispatchers.IO){
            trafficRepository.updateDensity(traffic)
        }
    }

}