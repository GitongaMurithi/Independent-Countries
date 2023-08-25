package com.example.countries.domain.model.repository

import com.example.countries.data.dto.CountryDto
import com.example.countries.data.dto1.CountryInfoDto

// Simulation of the actual api

interface CountryRepository {

    suspend fun getAllCountries() : List<CountryDto>

    suspend fun getCountryByName(name : String) : List<CountryInfoDto>
}