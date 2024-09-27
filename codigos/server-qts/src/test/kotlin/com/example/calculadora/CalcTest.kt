package com.example.calculadora

import kotlin.test.Test
import kotlin.test.assertEquals

class CalcTest {
    @Test
    fun testSum(){
        assertEquals(4, Calculator(1, 3).sum())
    }

    @Test
    fun testSub(){
        assertEquals(2, Calculator(4, 2).sub())
    }

    @Test
    fun testDivide(){
        assertEquals(5, Calculator(10, 2).divide())
    }

    @Test
    fun TestMultiply(){
        assertEquals(10, Calculator(5, 2).multiply())
    }

//    @Test
//    fun testErrorSum(){
//        assertEquals(2, Calculator(1, 3).sum())
//    }
}