package com.exercise.matipv2.ui.navigation

sealed class TabRowScreens(val route: String) {
    object TipCalculator : TabRowScreens("tipCalculator")
    object Events : TabRowScreens("events")
}