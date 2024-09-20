package com.aasjunior

import com.aasjunior.model.Products
import com.aasjunior.model.menu

fun main() {
    try {
        Products.initialize()
        Products.populateData()

        menu()
    }catch(e:Exception){
        println(e.message)
    }
}