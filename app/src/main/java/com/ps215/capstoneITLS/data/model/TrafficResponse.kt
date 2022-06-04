package com.ps215.capstoneITLS.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class TrafficResponse(
    val code: String,
    val message: String,
    val data: List<TrafficList>
)

@Parcelize
data class TrafficList(
    val _id: String,
    val name: String,
    val address: String,
    val latitude: String,
    val longitude: String
): Parcelable
