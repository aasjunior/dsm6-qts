package com.example.calculadora

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CalculatorTest {
    @Test
    fun `test sum of integers`() {
        val calc = Calculator(5, 3)
        assertEquals(8, calc.sum())
    }

    @Test
    fun `test sum of doubles`() {
        val calc = Calculator(5.5, 3.2)
        assertEquals(8.7, calc.sum())
    }

    @Test
    fun `test subtraction of integers`() {
        val calc = Calculator(10, 4)
        assertEquals(6, calc.sub())
    }

    @Test
    fun `test multiplication of integers`() {
        val calc = Calculator(3, 4)
        assertEquals(12, calc.multiply())
    }

    @Test
    fun `test division of integers`() {
        val calc = Calculator(10, 2)
        assertEquals(5, calc.divide())
    }

    @Test
    fun `test division by zero throws exception`() {
        val calc = Calculator(10, 0)
        val exception = assertFailsWith<IllegalArgumentException> {
            calc.divide()
        }
        assertEquals("Divisão por zero não é permitida!", exception.message)
    }
}