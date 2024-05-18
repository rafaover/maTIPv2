package com.exercise.matipv2.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.exercise.matipv2.components.common.MainNavigationBar
import com.exercise.matipv2.ui.navigation.NavigationGraph
import com.exercise.matipv2.ui.navigation.NavigationScreens

@SuppressLint("VisibleForTests")
@Composable
fun MainScreen(viewModel: MainScreenViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val navController = rememberNavController()

    Box(modifier = Modifier.fillMaxSize()) {
        NavigationGraph(
            viewModel = viewModel,
            navController = navController,
            uiState = uiState
        )
        MainNavigationBar(
            currentTabIndex = uiState.selectedTabIndex,
            selectedTabIndex = {
                viewModel.updateSelectedTabIndex(it)
                when (it) {
                    0 -> navController.navigate(NavigationScreens.TipCalculator.route)
                    1 -> navController.navigate(NavigationScreens.Events.route)
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}