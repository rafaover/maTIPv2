package com.exercise.matipv2.ui.tipcalculator

data class TipCalculatorScreenUiState(
    var roundUp: Boolean = false,
    var splitShare: Int = 0,
    var finalTip: String = ""
)
