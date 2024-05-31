package com.exercise.matipv2.util

import com.exercise.matipv2.data.local.model.Tip

fun tipListToString(
    stringList: List<Tip>
): String {
    return stringList.joinToString("\n") {tip ->
        tip.tipAmount
    }
}