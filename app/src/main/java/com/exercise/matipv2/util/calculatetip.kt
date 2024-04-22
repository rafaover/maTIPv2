package com.exercise.matipv2.util

import androidx.annotation.VisibleForTesting
import java.text.NumberFormat

@VisibleForTesting
internal fun calculateTip(
    amount: Double,
    tipPercent: Double = 5.0,
    roundUp: Boolean,
): String {
    var tip = tipPercent / 100 * amount
    if(roundUp)
        tip = kotlin.math.ceil(tip)
    return NumberFormat.getCurrencyInstance().format(tip)
}