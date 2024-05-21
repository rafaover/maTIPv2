package com.exercise.matipv2.data

data class MainScreenState(
    var tipAmount: String = "",
    var tipPercent: String = "",
    var roundUp: Boolean = false,
    var splitShare: Int = 0,
    var finalTip: String = "",
    var showAddEventDialog: Boolean = false,
    var eventName: String = "",
)
