package com.example.calculadora

data class Calculator(
    val n1: Number,
    val n2: Number
)

fun Calculator.sum(): Number{
    return if (n1 is Int && n2 is Int) {
        n1.toInt() + n2.toInt()
    } else {
        n1.toDouble() + n2.toDouble()
    }
}

fun Calculator.sub(): Number{
    return if (n1 is Int && n2 is Int) {
        n1.toInt() - n2.toInt()
    } else {
        n1.toDouble() - n2.toDouble()
    }
}

fun Calculator.multiply(): Number{
    return if (n1 is Int && n2 is Int) {
        n1.toInt() * n2.toInt()
    } else {
        n1.toDouble() * n2.toDouble()
    }
}

fun Calculator.divide(): Number{
    require(n2.toDouble() != 0.0) { "Divisão por zero não é permitida!" }

    return if (n1 is Int && n2 is Int) {
        n1.toInt() / n2.toInt()
    } else {
        n1.toDouble() / n2.toDouble()
    }
}