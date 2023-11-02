package com.example.countries.data.repository

import com.example.countries.data.CountriesApi
import com.example.countries.data.dto.CountryDto
import com.example.countries.domain.model.repository.CountryRepository
import javax.inject.Inject

class CountryRepositoryImplementation @Inject constructor(
    private val countryApi: CountriesApi
): CountryRepository {

    override suspend fun getAllCountries(): List<CountryDto> {
        return countryApi.getAllCountries()
    }

    override suspend fun getCountryByName(name: String): List<CountryDto> {
        return countryApi.getCountryByName(name = name)
    }
}