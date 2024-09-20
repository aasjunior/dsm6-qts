package com.aasjunior.model

fun menu(){
    while(true){
        try{
            when(val option = displayMenu()){
                1 -> {
                    val (n1, n2) = captureNumber()
                    println("Resultado: ${Calculator(n1, n2).sum()}")
                }
                2 -> {
                    val (n1, n2) = captureNumber()
                    println("Resultado: ${Calculator(n1, n2).sub()}")
                }
                3 -> {
                    val (n1, n2) = captureNumber()
                    println("Resultado: ${Calculator(n1, n2).multiply()}")
                }
                4 -> {
                    val (n1, n2) = captureNumber()
                    println("Resultado: ${Calculator(n1, n2).divide()}")
                }
                5 -> {
                    println("Saindo...")
                    break
                }
                else -> println("Opção inválida. Tente novamente.")
            }
        }catch(e: IllegalArgumentException) {
            println("Erro: ${e.message}")
        }catch(e: Exception) {
            println("Erro inesperado: ${e.message}")
        }
        println()
    }
}

fun displayMenu(): Int{
    println("Escolha a operação:")
    println("1. Adição")
    println("2. Subtração")
    println("3. Multiplicação")
    println("4. Divisão")
    println("5. Sair")
    print("Opção: ")
    return readln().toIntOrNull() ?: -1
}

fun captureNumber(): Pair<Number, Number>{
    print("Digite o primeiro número: ")
    val n1 = readNumber()
    print("Digite o segundo número: ")
    val n2 = readNumber()
    return Pair(n1, n2)
}

fun readNumber(): Number{
    val input = readln()

    // Tenta converter para Int primeiro, se falhar, converte para Double
    return input.toIntOrNull() ?: input.toDoubleOrNull() ?: error("Entrada inválida.")
}