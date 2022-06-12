package com.ps215.capstoneITLS.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ps215.capstoneITLS.Injection
import com.ps215.capstoneITLS.data.Repository
import com.ps215.capstoneITLS.ui.dashboard.DashboardViewModel
import com.ps215.capstoneITLS.ui.map.MapViewModel
import com.ps215.capstoneITLS.ui.trafficdetail.TrafficDetailViewModel

class ViewModelFactory private constructor(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(repository) as T
        } else if(modelClass.isAssignableFrom(MapViewModel::class.java)){
            return MapViewModel(repository) as T
        }
        else if(modelClass.isAssignableFrom(TrafficDetailViewModel::class.java)){
            return TrafficDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}