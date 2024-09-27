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

        post("/contas/depositar/{NumeroConta}/{Valor}") {
            try {
                val numeroConta = call.parameters["NumeroConta"]?.toIntOrNull()
                val valor = call.parameters["Valor"]?.toDoubleOrNull()

                if(numeroConta != null){
                    val conta = ContaBancaria.buscarConta(numeroConta)
                    if(conta != null){
                        conta.depositar(valor!!)
                        call.respondText("Depósito realizado com sucesso. Novo saldo: R$ ${conta.consultarSaldo()}")
                    }else{
                        call.respondText("Conta não encontrada.")
                    }
                }else{
                    call.respondText("Número de conta inválido.")
                }
            }catch(e:Exception){
                call.respondText("Erro: ${e.message}")
            }
        }

        post("/contas/sacar/{NumeroConta}/{Valor}") {
            try {
                val numeroConta = call.parameters["NumeroConta"]?.toIntOrNull()
                val valor = call.parameters["Valor"]?.toDoubleOrNull()

                if(numeroConta != null){
                    val conta = ContaBancaria.buscarConta(numeroConta)
                    if(conta != null){
                        conta.sacar(valor!!)
                        call.respondText("Saque realizado com sucesso. Novo saldo: R$ ${conta.consultarSaldo()}")
                    }else{
                        call.respondText("Conta não encontrada.")
                    }
                }else{
                    call.respondText("Número de conta inválido.")
                }
            }catch(e:Exception){
                call.respondText("Erro: ${e.message}")
            }
        }

    }
}