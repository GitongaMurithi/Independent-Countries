package com.example.countries.presentation.country_info

import com.example.countries.domain.model.Country

data class InfoScreenState(
    val isLoading: Boolean = false,
    val error: String = "",
    val country: List<Country>? = null
)
