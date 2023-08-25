package com.example.countries.presentation.countries_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.countries.common.Resource
import com.example.countries.domain.use_cases.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val useCase: GetCountriesUseCase
) : ViewModel() {

    private var _state: MutableState<CountryScreenState> = mutableStateOf(CountryScreenState())
    val state: State<CountryScreenState> = _state

    lateinit var controller: NavController

    private var _query = mutableStateOf(value = "")
    val query: State<String> = _query

//    private var _isActive = MutableStateFlow(value = false)
//    val isActive = _isActive.asStateFlow()

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
    }

    // this function which action the ui sends to the viewModel

    fun onAction(action: UserAction) {
        when (action) {
            is UserAction.PopBackStack -> {
                if (_query.value.isNotEmpty()) {
                    _query.value = ""
                    getCountries()
                } else {
                    controller.popBackStack()
                }
            }
        }
    }

    init {
        getCountries()
    }

    fun getCountries(search: String = "") {
        useCase().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _state.value = CountryScreenState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = CountryScreenState(country = if (search.isEmpty()) {
                        resource.data ?: emptyList()
                    } else {
                        resource.data?.filter { country ->
                            country.name.contains(search, ignoreCase = true)
                        } ?: emptyList()
                    }
                    )
                }

                is Resource.Error -> {
                    _state.value =
                        CountryScreenState(error = "Could not reach the server! Check your internet connection")
                }
            }
        }.launchIn(viewModelScope)
    }

}