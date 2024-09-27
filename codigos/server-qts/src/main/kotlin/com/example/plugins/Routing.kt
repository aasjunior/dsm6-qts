package com.example.plugins

import com.example.caixa.CaixaAPI
import com.example.calculadora.CalculadoraAPI
import io.ktor.server.application.*

fun Application.configureRouting() {
    CalculadoraAPI()
    CaixaAPI()
}