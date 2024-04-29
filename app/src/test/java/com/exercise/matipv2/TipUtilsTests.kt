package com.exercise.matipv2

import com.exercise.matipv2.util.calculateTip
import com.exercise.matipv2.util.splitTipValue
import com.exercise.matipv2.util.stringAmountToDouble
import org.junit.Assert.assertEquals
import org.junit.Test

class TipUtilsTests() {
    @Test
    fun testStringAmountToDouble() {
        val amount = "100"
        val expected = 100.0
        val actual = stringAmountToDouble(amount)
        assertEquals(expected, actual, 0.0)
    }

    @Test
    fun testCalculateTip() {
        val amount = "100"
        val tipPercent = "15"
        val roundUp = false
        val expected = "$15.00"
        val actual = calculateTip(amount, tipPercent, roundUp)
        assertEquals(expected, actual)
    }

    @Test
    fun testSplitTipValue() {
        val tipAmount = "15.0"
        val splitShare = 2
        val expected = 7.5
        val actual = splitTipValue(tipAmount, splitShare)
        assertEquals(expected, actual, 0.0)
    }

    @Test
    fun testFinalTip() {
        val amount = "100"
        val tipPercent = "15"
        val roundUp = false
        val expected = "$15.00"
    }
}