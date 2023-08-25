package com.example.countries.data.dto


import com.google.gson.annotations.SerializedName

data class Demonyms(
    @SerializedName("eng")
    val eng: Eng,
    @SerializedName("fra")
    val fra: Fra
)