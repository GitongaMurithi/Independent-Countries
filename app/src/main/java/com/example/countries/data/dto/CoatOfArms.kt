package com.example.countries.data.dto


import com.google.gson.annotations.SerializedName

data class CoatOfArms(
    @SerializedName("png")
    val png: String,
    @SerializedName("svg")
    val svg: String
)