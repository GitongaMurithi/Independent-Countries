package com.example.countries

sealed class Navigator (val route : String) {
    object Home : Navigator(route = "home")
    object Info : Navigator(route = "info")
}
