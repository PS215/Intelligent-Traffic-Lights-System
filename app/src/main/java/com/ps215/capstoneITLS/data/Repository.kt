package com.ps215.capstoneITLS.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ps215.capstoneITLS.api.ApiConfig
import com.ps215.capstoneITLS.data.database.Traffic
import com.ps215.capstoneITLS.data.database.TrafficDao
import com.ps215.capstoneITLS.data.model.TrafficList
import com.ps215.capstoneITLS.data.model.TrafficResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val trafficDao: TrafficDao) {
    val listTraffics = MutableLiveData<ArrayList<TrafficList>>()

    fun getDensity(newid: String): LiveData<Traffic> = trafficDao.getDensity(newid)

//    suspend fun updateDensity(newid: String, newdensity:Int) = trafficDao.updateDensity(newid, newdensity)

    suspend fun updateDensity(traffic: Traffic) {
        trafficDao.updateDensity(traffic)
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(trafficDao: TrafficDao): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(trafficDao).apply { instance = this }
            }
    }

    fun setTraffic(){
        val client = ApiConfig.getApiService().getTraffic()
        client.enqueue(object : Callback<TrafficResponse> {
            override fun onResponse(call: Call<TrafficResponse>, response: Response<TrafficResponse>) {
                if(response.isSuccessful){
                    listTraffics.postValue(response.body()?.data as ArrayList<TrafficList>)
                }
            }

            override fun onFailure(call: Call<TrafficResponse>, t: Throwable) {
                t.message?.let { Log.d("onFailure", it) }
            }
        })
    }

    fun getTraffic(): LiveData<ArrayList<TrafficList>>{
        return listTraffics
    }
}