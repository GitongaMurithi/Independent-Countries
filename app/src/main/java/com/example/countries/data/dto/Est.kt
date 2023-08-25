package com.example.countries.data.dto


import com.google.gson.annotations.SerializedName

data class Est(
    @SerializedName("common")
    val common: String,
    @SerializedName("official")
    val official: String
)