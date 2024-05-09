package com.exercise.matipv2.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.exercise.matipv2.data.MainScreenState
import com.exercise.matipv2.data.MatipRepository
import com.exercise.matipv2.util.calculateTip
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel(
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenState())
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

    fun updateSelectedTabIndex(index: Int) {
        _uiState.value = uiState.value.copy(selectedTabIndex = index)
    }

    fun increaseCounter() {
        _uiState.value = uiState.value.copy(splitShare = uiState.value.splitShare + 1)
    }

    fun decreaseCounter() {
        if (uiState.value.splitShare > 0) {
            _uiState.value = uiState.value.copy(splitShare = uiState.value.splitShare - 1)
        }
    }

    @SuppressLint("VisibleForTests")
    fun finalTip(): String {
        return calculateTip(
            amount = uiState.value.amountInput,
            tipPercent = uiState.value.tipPercentInput,
            roundUp = uiState.value.roundUp,
            tipSplit = uiState.value.splitShare
        )
    }
}

