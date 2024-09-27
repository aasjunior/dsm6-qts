package com.example.caixa

import kotlin.test.*

class CaixaTest {

    @BeforeTest
    fun setup(){
        Database.initialize()
        Database.populateData()
    }

    @Test
    fun testDepositar(){
        val conta = ContaBancaria.buscarConta(101)
        conta!!.depositar(200.00)
        assertEquals(700.0, conta.consultarSaldo())
    }

    @Test
    fun testSacar(){
        val conta = ContaBancaria.buscarConta(102)
        conta!!.sacar(500.00)
        assertEquals(1000.0, conta.consultarSaldo())
    }

    @Test
    fun testSaldoInsuficiente(){
        val conta = ContaBancaria.buscarConta(101)
        val exception = assertFailsWith<IllegalArgumentException>{
            conta!!.sacar(1000.0)
        }
        assertEquals("Saldo insuficiente para o saque.", exception.message)
    }

    @Test
    fun testDepositoNegativo(){
        val conta = ContaBancaria.buscarConta(101)
        val exception = assertFailsWith<IllegalArgumentException>{
            conta!!.depositar(-500.00)
        }
        assertEquals("O valor do depósito deve ser positivo.", exception.message)
    }

    @Test
    fun testSaqueNegativo(){
        val conta = ContaBancaria.buscarConta(102)
        val exception = assertFailsWith<IllegalArgumentException>{
            conta!!.sacar(-500.00)
        }
        assertEquals("O valor de saque deve ser positivo.", exception.message)
    }
}