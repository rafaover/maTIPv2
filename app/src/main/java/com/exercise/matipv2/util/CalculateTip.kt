package com.exercise.matipv2.util

import androidx.annotation.VisibleForTesting
import java.text.NumberFormat

@VisibleForTesting
internal fun calculateTip(
    amount: String,
    tipPercent: String,
    roundUp: Boolean,
): String {
    var tip = stringAmountToDouble(tipPercent) / 100 * stringAmountToDouble(amount)
    if(roundUp)
        tip = kotlin.math.ceil(tip)
    return NumberFormat.getCurrencyInstance().format(tip)
}