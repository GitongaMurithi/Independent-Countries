package com.example.countries.common

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.countries.Navigator
import com.example.countries.presentation.countries_list.CountriesListScreen
import com.example.countries.presentation.countries_list.CountryViewModel
import com.example.countries.presentation.country_info.InfoScreen

@Composable
fun NavigationSetUp(
    navHostController: NavHostController,
) {
    val viewModel: CountryViewModel = hiltViewModel()
    val state = viewModel.state
    NavHost(
        navController = navHostController,
        startDestination = Navigator.Home.route
    ) {
        composable(route = Navigator.Home.route) {
            CountriesListScreen(
                state = state,
                onCountryClick = { country ->
                    navHostController.navigate(Navigator.Info.route + "/${country.name?.common}")
                },
                onAction = viewModel::onAction
            )
        }
        composable(
            route = Navigator.Info.route + "/{name}",
            arguments = listOf(navArgument(name = "name") {
                type = NavType.StringType
            })) {
            InfoScreen(
                onBAckPressed = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}