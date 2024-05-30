package com.exercise.matipv2.data

data class MainScreenState(
    var tipAmount: String = "",
    var tipPercent: String = "",
    var roundUp: Boolean = false,
    var splitShare: Int = 0,
    var finalTip: String = "",
    var eventName: String = "",
    var showDeleteEventDialog: Boolean = false,
    var showSnackBar: Boolean = false
)
