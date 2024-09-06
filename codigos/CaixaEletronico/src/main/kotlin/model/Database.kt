package com.aasjunior.model

import java.sql.Connection
import java.sql.DriverManager

object Database {
    private const val URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
    private const val USER = "sa"
    private const val PASSWORD = ""

    fun connect(): Connection{
        return DriverManager.getConnection(URL, USER, PASSWORD)
    }

    fun initialize(){
        connect().use { connection ->
            connection.createStatement().use { statement ->
                statement.execute(
                    """
                       CREATE TABLE IF NOT EXISTS ContaBancaria (
                           numero INT PRIMARY KEY,
                           saldo DOUBLE
                       ) 
                    """.trimIndent()
                )
            }
        }
    }

    fun populateData(){
        connect().use { connection ->
            connection.createStatement().use { statement ->
                statement.execute(
                    """
                    MERGE INTO ContaBancaria(numero, saldo) 
                    KEY(numero) 
                    VALUES (101, 500.0), (102, 1500.0);
                """.trimIndent()
                )
            }
        }
    }

}