package com.example.countries.presentation.countries_list

import com.example.countries.domain.model.Country

sealed class UserAction {
    class OnSearchQueryChanged(val searchQuery : String) : UserAction()
    class OnCountryClick(val country: Country) : UserAction()
    object OnCloseIconClick : UserAction()
    object OnSearchIconClick : UserAction()
    object OnRefresh: UserAction()
}
