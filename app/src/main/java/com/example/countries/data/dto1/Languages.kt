package com.example.countries.data.dto1


import com.google.gson.annotations.SerializedName

data class Languages(
    @SerializedName("eng")
    val eng: String,
    @SerializedName("swa")
    val swa: String
)