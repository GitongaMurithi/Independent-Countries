package com.example.countries.presentation.countries_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.common.Resource
import com.example.countries.data.dto.toCountry
import com.example.countries.domain.use_cases.GetCountriesUseCase
import com.example.countries.domain.use_cases.GetCountryByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryByNameUseCase: GetCountryByNameUseCase
) : ViewModel() {

    var state by mutableStateOf(CountryScreenState())
        private set

    private var job: Job? = null

    // this function represents which action the ui sends to the viewModel
    fun onAction(action: UserAction) {
        when (action) {
            UserAction.OnCloseIconClick -> {
                state = state.copy(isSearchBarVisible = false)
                getCountries()
            }

            UserAction.OnSearchIconClick -> {
                state = state.copy(
                    isSearchBarVisible = true,
                    country = emptyList()
                )
            }

            is UserAction.OnSearchQueryChanged -> {
                state = state.copy(
                    searchQuery = action.searchQuery
                )
                job?.cancel()
                job = viewModelScope.launch {
                    delay(1000)
                    searchCountry(name = state.searchQuery)
                }
            }

            is UserAction.OnCountryClick -> {
                state = state.copy(selectedCountry = action.country)
            }

            is UserAction.OnRefresh -> {
                if (state.searchQuery.isEmpty()) {
                    getCountries()
                } else {
                    searchCountry(name = state.searchQuery)
                }
            }
        }
    }

    init {
        getCountries()
    }

    private fun getCountries() {
        getCountriesUseCase().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    state = state.copy(
                        isLoading = true,
                        error = null
                    )
                }

                is Resource.Success -> {
                    state = state.copy(
                        country = resource.data ?: emptyList(),
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        error = "You could be offline. Or the server is down",
                        isLoading = false,
                        country = emptyList()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun searchCountry(name: String = "") {
        if (name.isEmpty()) {
            return
        }
        getCountryByNameUseCase(name = name).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    state = state.copy(
                        isLoading = true,
                        error = null
                    )
                }

                is Resource.Success -> {
                    state = state.copy(
                        country = resource.data?.map { it.toCountry() } ?: emptyList(),
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    state = if (state.searchQuery.isEmpty()) {
                        state.copy(
                            error = "You could be offline. Or the server is down",
                            isLoading = false,
                            country = emptyList()
                        )
                    } else {
                        state.copy(
                            error = resource.message ?: "You could be offline. Or the server is down",
                            isLoading = false,
                            country = emptyList()
                        )
                    }

                }
            }
        }.launchIn(viewModelScope)
    }
}

