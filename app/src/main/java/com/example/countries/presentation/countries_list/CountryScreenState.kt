package com.example.countries.presentation.countries_list

import com.example.countries.domain.model.Country

// This represents the very initial state of the screen

data class CountryScreenState (
    val isLoading : Boolean = false,
    val country : List<Country>  = emptyList(),
    val error : String = ""
)