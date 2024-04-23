package com.exercise.matipv2.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.exercise.matipv2.model.MainLayoutState
import com.exercise.matipv2.util.calculateTip
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainLayoutViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainLayoutState())
    val uiState = _uiState.asStateFlow()

    fun updateAmountInput(amount: String) {
        _uiState.value = uiState.value.copy(amountInput = amount)
    }

    fun updateTipPercentInput(tipPercent: String) {
        _uiState.value = uiState.value.copy(tipPercentInput = tipPercent)
    }

    fun updateRoundUp(roundUp: Boolean) {
        _uiState.value = uiState.value.copy(roundUp = roundUp)
    }

    private fun amountToDouble(): Double {
        return uiState.value.amountInput.toDoubleOrNull() ?: 0.0
    }

    private fun tipPercentToDouble(): Double {
        return uiState.value.tipPercentInput.toDoubleOrNull() ?: 0.0
    }

    @SuppressLint("VisibleForTests")
    fun finalTip(): String {
        return calculateTip(amountToDouble(), tipPercentToDouble(), uiState.value.roundUp)
    }
}
