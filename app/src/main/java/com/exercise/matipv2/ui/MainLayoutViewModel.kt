package com.exercise.matipv2.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.exercise.matipv2.model.MainLayoutState
import com.exercise.matipv2.util.calculateTip
import com.exercise.matipv2.util.splitTipValue
import com.exercise.matipv2.util.stringAmountToDouble
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

    fun increaseCounter() {
        _uiState.value = uiState.value.copy(splitShare = uiState.value.splitShare + 1)
    }

    fun decreaseCounter() {
        _uiState.value = uiState.value.copy(splitShare = uiState.value.splitShare - 1)
    }

    @SuppressLint("VisibleForTests")
    fun finalTip(): String {
        return if (uiState.value.splitShare > 1) {
            splitTipValue(
                amount = uiState.value.amountInput,
                splitShare = uiState.value.splitShare
            ).toString()
        } else {
            calculateTip(
                amount = uiState.value.amountInput,
                tipPercent = uiState.value.tipPercentInput,
                roundUp = uiState.value.roundUp
            )
        }
    }
}
