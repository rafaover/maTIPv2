package com.exercise.matipv2.util

fun splitTipValue(amount: String, splitShare: String): Double {
    val amountDouble = stringAmountToDouble(amount)
    val splitShareInt = splitShare.toInt()
    return if (splitShareInt == 0) amountDouble else amountDouble / splitShareInt
}