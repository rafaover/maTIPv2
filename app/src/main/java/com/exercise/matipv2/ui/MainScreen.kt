package com.exercise.matipv2.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.exercise.matipv2.components.common.TopTabRow
import com.exercise.matipv2.ui.tipcalculator.TipCalculatorScreen

@SuppressLint("VisibleForTests")
@Composable
fun MainScreen(viewModel: MainScreenViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column {
        TopTabRow(
            currentTabIndex = uiState.selectedTabIndex,
            selectedTabIndex = { viewModel.updateSelectedTabIndex(it) }
        )
        TipCalculatorScreen(
            viewModel = viewModel,
            uiState = uiState
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainLayoutPreview() {
    MainScreen()
}