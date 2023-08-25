package com.example.countries.domain.model

import com.example.countries.data.dto1.Car
import com.example.countries.data.dto1.Demonyms
import com.example.countries.data.dto1.Flags
import com.example.countries.data.dto1.Name

data class CountryInfo(
    val name : Name?,
    val population : Int?,
    val region : String?,
    val demonym : Demonyms?,
    val area : Double?,
    val car : Car?,
    val cca2 : String?,
    val cca3 : String?,
    val ccn3 : String?,
    val fifa : String?,
    val startOfWeek : String?,
    val subregion : String?,
    val borders : List<String>?,
    val flags : Flags?,
    val coatOfArms : String?,
    val cioc : String?,
)