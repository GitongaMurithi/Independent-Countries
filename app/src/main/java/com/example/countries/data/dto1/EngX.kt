package com.example.countries.data.dto1


import com.google.gson.annotations.SerializedName

data class EngX(
    @SerializedName("common")
    val common: String,
    @SerializedName("official")
    val official: String
)