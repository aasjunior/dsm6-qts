package com.example.caixa

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.CaixaAPI(){
    routing {
        get("/conta"){
            call.respondText("Conta API")
        }

        post("/contas/{NumeroConta}") {
            val numeroConta = call.parameters["NumeroConta"]?.toIntOrNull()
            if(numeroConta != null){
                val conta = ContaBancaria.buscarConta(numeroConta)
                if(conta != null){
                    call.respondText("Conta encontrada: ${conta.consultarSaldo()}")
                }else{
                    call.respondText("Conta não encontrada.")
                }
            }else{
                call.respondText("Número de conta inválido.")
            }
        }
    }
}