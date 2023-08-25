package com.example.countries.data.dto1


import com.google.gson.annotations.SerializedName

data class Idd(
    @SerializedName("root")
    val root: String,
    @SerializedName("suffixes")
    val suffixes: List<String>
)