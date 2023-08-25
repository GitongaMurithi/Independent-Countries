package com.example.countries.data.dto1


import com.google.gson.annotations.SerializedName

data class NativeName(
    @SerializedName("eng")
    val eng: EngX,
    @SerializedName("swa")
    val swa: Swa
)