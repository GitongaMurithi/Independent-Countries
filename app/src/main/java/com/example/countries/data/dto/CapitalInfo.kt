package com.example.countries.data.dto


import com.google.gson.annotations.SerializedName

data class CapitalInfo(
    @SerializedName("latlng")
    val latlng: List<Double>
)