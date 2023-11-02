package com.example.countries.presentation.countries_list

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.countries.domain.model.Country
import com.example.countries.presentation.countries_list.components.CountryComponent
import com.example.countries.presentation.countries_list.components.CountrySearchBar
import com.example.countries.presentation.countries_list.components.CountryTopAppBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CountriesListScreen(
    state: CountryScreenState,
    onCountryClick: (Country) -> Unit,
    onAction: (UserAction) -> Unit // ui action that shall be sent to the viewModel
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Crossfade(targetState = state.isSearchBarVisible, label = "") { isVisible ->
            if (isVisible) {
                Column {
                    CountrySearchBar(
                        modifier = Modifier.focusRequester(focusRequester),
                        onSearchIconClick = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        },
                        onCloseIconClick = {
                            onAction(UserAction.OnCloseIconClick)
                        },
                        value = state.searchQuery,
                        onValueChange = {
                            onAction(UserAction.OnSearchQueryChanged(it))
                        }
                    )
                    CountriesList(
                        state = state,
                        onCountryClick = onCountryClick,
                        onRetry = {
                            onAction(UserAction.OnRefresh)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.background)
                    )
                }
            } else {
                Scaffold(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        CountryTopAppBar(
                            scrollBehavior = scrollBehavior,
                            onActionButtonClick = {
                                coroutineScope.launch {
                                    delay(500)
                                    focusRequester.requestFocus()
                                }
                                onAction(UserAction.OnSearchIconClick)
                            }
                        )
                    }
                ) { paddingValues ->
                    CountriesList(
                        state = state,
                        onCountryClick = onCountryClick,
                        onRetry = {
                            onAction(UserAction.OnRefresh)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.background)
                            .padding(paddingValues)
                    )
                }
            }
        }

    }

}

@Composable
fun CountriesList(
    state: CountryScreenState,
    onCountryClick: (Country) -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
        ) {
            state.country?.let {
                items(it.sortedBy { country->
                    country.name?.official }) { country ->
                    CountryComponent(
                        country = country,
                        onCountryClick = {
                            onCountryClick(country)
                        })
                }
            }
        }

        if (state.error != null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = state.error,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)

                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = onRetry
                    ) {
                        Text(
                            text = "Refresh",
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

            }

        }
        if (state.isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}


