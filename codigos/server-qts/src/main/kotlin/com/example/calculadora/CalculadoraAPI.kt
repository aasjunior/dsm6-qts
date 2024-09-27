package com.example.calculadora

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlin.text.toDoubleOrNull

fun Application.CalculadoraAPI() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/sum/{a}/{b}") {
            val a = call.parameters["a"]?.toDoubleOrNull() ?: 0.0
            val b = call.parameters["b"]?.toDoubleOrNull() ?: 0.0

            call.respondText(Calculator(a, b).sum().toString())
        }

        get("/sub/{a}/{b}") {
            val a = call.parameters["a"]?.toDoubleOrNull() ?: 0.0
            val b = call.parameters["b"]?.toDoubleOrNull() ?: 0.0

            call.respondText(Calculator(a, b).sub().toString())
        }

        get("/mult/{a}/{b}") {
            val a = call.parameters["a"]?.toDoubleOrNull() ?: 0.0
            val b = call.parameters["b"]?.toDoubleOrNull() ?: 0.0

            call.respondText(Calculator(a, b).multiply().toString())
        }

        get("/div/{a}/{b}") {
            val a = call.parameters["a"]?.toDoubleOrNull() ?: 0.0
            val b = call.parameters["b"]?.toDoubleOrNull() ?: 0.0

            if (b == 0.0)
                call.respondText("Divisão por zero não é permitida!")
            else
                call.respondText(Calculator(a, b).divide().toString())
        }
    }
}
