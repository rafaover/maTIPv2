package com.exercise.matipv2.model

data class MainLayoutState(
    var amountInput: String = "",
    var tipPercentInput: String = "",
    var roundUp: Boolean = false,
    var splitShare: Int = 0
)
