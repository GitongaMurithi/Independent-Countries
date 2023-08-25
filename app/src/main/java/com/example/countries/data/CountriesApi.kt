package com.example.countries.data

import com.example.countries.data.dto.CountryDto
import com.example.countries.data.dto1.CountryInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesApi {

    @GET("/v3.1/independent")
    suspend fun getAllCountries() : List<CountryDto>

    @GET("/v3.1/name/{name}")
    suspend fun getCountryByName(@Path(value = "name") name : String) : List<CountryInfoDto>
}