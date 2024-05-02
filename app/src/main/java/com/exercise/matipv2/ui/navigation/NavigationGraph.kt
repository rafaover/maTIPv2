package com.exercise.matipv2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.exercise.matipv2.model.MainScreenState
import com.exercise.matipv2.ui.MainScreenViewModel
import com.exercise.matipv2.ui.events.EventsScreen
import com.exercise.matipv2.ui.tipcalculator.TipCalculatorScreen

@Composable
fun NavigationGraph(
    viewModel: MainScreenViewModel,
    navController: NavHostController,
    uiState: MainScreenState
) {
    NavHost(
        navController = navController,
        startDestination = TabRowScreens.TipCalculator.route
    ) {
        composable(TabRowScreens.TipCalculator.route) {
            TipCalculatorScreen(
                viewModel = viewModel,
                uiState = uiState
            )
        }
        composable(TabRowScreens.Events.route) {
            EventsScreen()
        }
    }
}