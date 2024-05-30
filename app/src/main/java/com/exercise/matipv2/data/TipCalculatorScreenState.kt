package com.exercise.matipv2.data

data class TipCalculatorScreenState(
    var tipAmount: String = "",
    var tipPercent: String = "",
    var roundUp: Boolean = false,
    var splitShare: Int = 0,
    var finalTip: String = ""
)
