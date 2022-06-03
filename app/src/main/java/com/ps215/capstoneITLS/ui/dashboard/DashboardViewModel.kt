package com.ps215.capstoneITLS.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ps215.capstoneITLS.database.Repository
import com.ps215.capstoneITLS.database.Traffic

class DashboardViewModel(private val trafficRepository: Repository) : ViewModel() {
    fun getAllTraffic(): LiveData<List<Traffic>> = trafficRepository.getAllTraffic()
}
