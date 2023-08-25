package com.example.countries.data.dto1


import com.google.gson.annotations.SerializedName

data class UGX(
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String
)