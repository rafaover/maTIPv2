package com.exercise.matipv2.ui

import androidx.lifecycle.ViewModel
import com.exercise.matipv2.model.MainLayoutState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainLayoutViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MainLayoutState())
    val uiState = _uiState.asStateFlow()

//    val amount = amountInput.toDoubleOrNull() ?: 0.0
//    val tipPercent = tipPercentInput.toDoubleOrNull() ?: 0.0
//    val tip = calculateTip(amount, tipPercent, roundUp)

}
