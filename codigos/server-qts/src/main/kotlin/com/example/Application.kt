package com.example

import com.example.caixa.Database
import com.example.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    try {
        Database.initialize()
        Database.populateData()
    }catch(e:Exception){
        println("Error: \n${e}")
    }
}
