package com.exercise.matipv2.ui.navigation

sealed class NavigationScreens(val route: String) {
    object TipCalculator : NavigationScreens("tipCalculator")
    object Events : NavigationScreens("events")
}