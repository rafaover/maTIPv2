package com.exercise.matipv2.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import com.exercise.matipv2.data.MainScreenState
import com.exercise.matipv2.data.NavBarItems
import com.exercise.matipv2.ui.MainScreenViewModel
import com.exercise.matipv2.ui.events.EventTipListScreen
import com.exercise.matipv2.ui.events.EventsScreen
import com.exercise.matipv2.ui.tipcalculator.TipCalculatorScreen

@Composable
fun NavigationGraph(
    viewModel: MainScreenViewModel,
    navController: NavHostController,
    uiState: MainScreenState,
    snackbarHostState: SnackbarHostState
) {
    NavHost(
        navController = navController,
        startDestination = NavBarItems.TipCalculator.route
    ) {
        composable(NavBarItems.TipCalculator.route) {
            TipCalculatorScreen(
                viewModel = viewModel,
                uiState = uiState,
                snackbarHostState = snackbarHostState
            )
        }
        composable(NavBarItems.Events.route) {
            EventsScreen(
                allEvents = viewModel.getAllEvents(),
                uiState = uiState,
                viewModel = viewModel,
                navigateTo = { event ->
                    navController.navigate("EventTipList/${event.id}")
                }
            )
        }
        dialog(
            route = "EventTipList/{eventId}",
            arguments = listOf(navArgument("eventId") { type = NavType.IntType })
        ) { navBackStackEntry ->
            val eventId = navBackStackEntry.arguments?.getInt("eventId")
                ?: error("eventId parameter wasn't found.")

            EventTipListScreen(
                viewModel = viewModel,
                eventId = eventId,
                onDismissRequest = { navController.navigateUp() },
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}