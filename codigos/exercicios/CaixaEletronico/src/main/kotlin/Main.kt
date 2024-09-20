package com.aasjunior

import com.aasjunior.model.Database
import com.aasjunior.model.menu

fun main() {
    try {
        Database.initialize()
        Database.populateData()

        menu()
    }catch(e:Exception){
        println("Error: \n${e}")
    }
}