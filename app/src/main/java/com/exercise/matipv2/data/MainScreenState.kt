package com.exercise.matipv2.data

data class MainScreenState(
    var amountInput: String = "",
    var tipPercentInput: String = "",
    var roundUp: Boolean = false,
    var splitShare: Int = 0,
    var finalTip: String = "",
    var selectedTabIndex: Int = 0,
    var showDialog: Boolean = false
)
