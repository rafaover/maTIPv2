package com.exercise.matipv2.util

fun splitTipValue(amount: String, splitShare: Int): Double {
    val amountDouble = stringAmountToDouble(amount)
    return if (splitShare == 0) {
        amountDouble
    } else {
        amountDouble / splitShare
    }
}