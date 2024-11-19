package com.aasjunior.model

import java.sql.Connection

class ContaBancaria(
    val numero: Int
) {
    private var saldo: Double = 0.0

    init {
        require(numero > 0 ) { "O número da conta deve ser positivo." }
        getSaldo()
    }

    private fun getSaldo() {
        Database.connect().use { connection ->
            connection.prepareStatement(
                "SELECT saldo FROM ContaBancaria WHERE numero = ?"
            ).apply {
                setInt(1, numero)
            }.executeQuery().use { resultSet ->
                if (resultSet.next()) {
                    saldo = resultSet.getDouble("saldo")
                }
            }
        }
    }

    fun depositar(valor: Double){
        require(valor > 0) { "O valor do depósito deve ser positivo." }
        saldo += valor
        atualizarSaldo()
    }

    fun sacar(valor: Double){
        require(valor > 0) { "O valor de saque deve ser positivo." }
        require(valor <= saldo) { "Saldo insuficiente para o saque." }
        saldo -= valor
        atualizarSaldo()
    }

    fun consultarSaldo(): Double{
        return saldo
    }

    private fun atualizarSaldo() {
        Database.connect().use { connection ->
            connection.prepareStatement(
                """
                MERGE INTO ContaBancaria (numero, saldo)
                KEY (numero)
                VALUES (?, ?)
                """.trimIndent()
            ).apply {
                setInt(1, numero)
                setDouble(2, saldo)
                executeUpdate()
            }
        }
    }

    private fun connect(): Connection {
        return Database.connect()
    }

    companion object{
        fun buscarConta(numero: Int): ContaBancaria? {
            return Database.connect().use { connection ->
                connection.prepareStatement(
                    "SELECT saldo FROM ContaBancaria WHERE numero = ?"
                ).apply {
                    setInt(1, numero)
                }.executeQuery().use { resultSet ->
                    if(resultSet.next())
                        ContaBancaria(numero)
                    else null
                }
            }
        }
    }
}