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

    @SuppressLint("VisibleForTests")
    fun finalTip(): String {
        return calculateTip(
            amount = stringAmountToDouble(uiState.value.amountInput),
            tipPercent = stringAmountToDouble(uiState.value.tipPercentInput),
            roundUp = uiState.value.roundUp
        )
    }

//    fun finalSplit(finalTip: Double): Double {
//        return splitTipValue(finalTip, uiState.value.splitShare)
//    }
}
