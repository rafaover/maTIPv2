package com.exercise.matipv2.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.exercise.matipv2.components.common.TopTabRow
import com.exercise.matipv2.ui.navigation.NavigationGraph
import com.exercise.matipv2.ui.navigation.TabRowScreens

@SuppressLint("VisibleForTests")
@Composable
fun MainScreen(viewModel: MainScreenViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val navController = rememberNavController()

    Column {
        TopTabRow(
            currentTabIndex = uiState.selectedTabIndex,
            selectedTabIndex = {
                viewModel.updateSelectedTabIndex(it)
                when (it) {
                    0 -> navController.navigate(TabRowScreens.TipCalculator.route)
                    1 -> navController.navigate(TabRowScreens.Events.route)
                }
            }
        )
        NavigationGraph(
            viewModel = viewModel,
            navController = navController,
            uiState = uiState
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainLayoutPreview() {
    MainScreen()
}