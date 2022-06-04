package com.ps215.capstoneITLS.api

import com.ps215.capstoneITLS.data.model.TrafficResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("getTraffics")
    fun getTraffic(): Call<TrafficResponse>
}