package com.exercise.matipv2

import com.exercise.matipv2.util.calculateTip
import com.exercise.matipv2.util.stringAmountToDouble
import org.junit.Assert.assertEquals
import org.junit.Test

class TipUtilsTests {
    @Test
    fun testStringAmountToDouble() {
        val amount = "100"
        val expected = 100.0
        val actual = stringAmountToDouble(amount)
        assertEquals(expected, actual, 0.0)
    }

    @Test
    fun testCalculateTip_noSplit() {
        val amount = "100"
        val tipPercent = "15"
        val roundUp = false
        val expected = "$15.00"
        val actual = calculateTip(amount, tipPercent, 1, roundUp)
        assertEquals(expected, actual)
    }

    @Test
    fun testCalculateTip_Split() {
        val amount = "200"
        val tipPercent = "10"
        val roundUp = false
        val expected = "$10.00"
        val actual = calculateTip(amount, tipPercent, 2, roundUp)
        assertEquals(expected, actual)
    }
}