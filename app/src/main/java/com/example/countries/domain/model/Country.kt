package com.example.countries.domain.model

import com.example.countries.data.dto.Car
import com.example.countries.data.dto.Demonyms
import com.example.countries.data.dto.Flags
import com.example.countries.data.dto.Name

data class Country(
    val capital: List<String>,
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
    val cioc : String?

)