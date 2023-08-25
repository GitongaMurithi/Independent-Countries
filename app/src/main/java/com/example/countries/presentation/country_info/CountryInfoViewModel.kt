package com.example.countries.presentation.country_info

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.common.Constants
import com.example.countries.common.Resource
import com.example.countries.domain.use_cases.GetCountryByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountryInfoViewModel @Inject constructor(
    private val useCase: GetCountryByNameUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _state: MutableState<InfoScreenState> = mutableStateOf(InfoScreenState())
    val state: State<InfoScreenState> = _state

    init {
        savedStateHandle.get<String>(Constants.NAME)?.let { name ->
            getCountry(name = name)
        }
    }

    private fun getCountry(name: String) {
        useCase(name).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    _state.value = InfoScreenState(country = resource.data)
                }

                is Resource.Loading -> {
                    _state.value = InfoScreenState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = InfoScreenState(error = "Could not reach the server! Check your internet connection")

                }
            }
        }.launchIn(viewModelScope)
    }
}