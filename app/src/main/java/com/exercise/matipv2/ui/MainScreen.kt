package com.exercise.matipv2.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.exercise.matipv2.components.MainNavigationBar
import com.exercise.matipv2.components.common.MainTopBar
import com.exercise.matipv2.ui.navigation.NavigationGraph

@SuppressLint("VisibleForTests")
@Composable
fun MainScreen(viewModel: MainScreenViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val navController = rememberNavController()

    Scaffold(
        topBar = { MainTopBar() },
        bottomBar = { MainNavigationBar(navController = navController) }
    ) {
        Column(modifier = Modifier.padding(it)) {
            NavigationGraph(
                viewModel = viewModel,
                navController = navController,
                uiState = uiState
            )
        }
    }
}