package com.example.plugins

import com.example.caixa.CaixaAPI
import com.example.calculadora.CalculatorAPI
import io.ktor.server.application.*

fun Application.configureRouting() {
    CalculatorAPI()
    CaixaAPI()
}