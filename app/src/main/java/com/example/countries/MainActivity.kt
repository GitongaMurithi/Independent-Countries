package com.example.countries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.countries.presentation.countries_list.CountriesListScreen
import com.example.countries.presentation.countries_list.CountryViewModel
import com.example.countries.presentation.country_info.InfoScreen
import com.example.countries.ui.theme.CountriesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesTheme {
                Box{
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = Navigator.Home.route ) {
                        composable(route = Navigator.Home.route) {
                            CountriesListScreen(navController = navController)
                        }
                        composable(route = Navigator.Info.route + "/{name}") {
                            InfoScreen()
                        }
                    }

                }
            }
        }
    }
}
