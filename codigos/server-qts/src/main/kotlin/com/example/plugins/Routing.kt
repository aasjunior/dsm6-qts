package com.example.plugins

import com.example.calculadora.Calculator
import com.example.calculadora.divide
import com.example.calculadora.multiply
import com.example.calculadora.sub
import com.example.calculadora.sum
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/sum/{a}/{b}"){
            val a = call.parameters["a"]?.toDoubleOrNull() ?: 0.0
            val b = call.parameters["b"]?.toDoubleOrNull() ?: 0.0

            call.respondText(Calculator(a, b).sum().toString())
        }

        get("/sub/{a}/{b}"){
            val a = call.parameters["a"]?.toDoubleOrNull() ?: 0.0
            val b = call.parameters["b"]?.toDoubleOrNull() ?: 0.0

            call.respondText(Calculator(a, b).sub().toString())
        }

        get("/mult/{a}/{b}") {
            val a = call.parameters["a"]?.toDoubleOrNull() ?: 0.0
            val b = call.parameters["b"]?.toDoubleOrNull() ?: 0.0

            call.respondText(Calculator(a, b).multiply().toString())
        }

        get("/div/{a}/{b}"){
            val a = call.parameters["a"]?.toDoubleOrNull() ?: 0.0
            val b = call.parameters["b"]?.toDoubleOrNull() ?: 0.0

            if(b == 0.0)
                call.respondText("Divisão por zero não é permitida!")
            else
                call.respondText(Calculator(a, b).divide().toString())
        }
    }
}
