package com.aasjunior.model

fun menu(){
    while(true) {
        try {
            when(displayMenu()){
                0 -> {
                    teste()
                }
                1 -> {
                    val conta = obterConta()

                    print("Digite o valor do saque: ")
                    val valor = readln().toDouble()
                    conta.sacar(valor)
                    println("Saque realizado com sucesso. Novo saldo: R$ ${conta.consultarSaldo()}")
                }
                2 -> {
                    val conta = obterConta()
                    print("Digite o valor do depósito: ")
                    val valor = readln().toDouble()
                    conta.depositar(valor)
                    println("Depósito realizado com sucesso. Novo saldo: R$ ${conta.consultarSaldo()}")
                }
                3 -> {
                    val conta = obterConta()
                    println("Saldo da conta ${conta.numero}: R$ ${conta.consultarSaldo()}")
                }
                4 -> {
                    println("Saindo...")
                    break
                }
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
    println("0. Teste de Mesa")
    println("1. Sacar")
    println("2. Depositar")
    println("3. Consultar Saldo")
    println("4. Sair")
    print("Opção: ")
    return readln().toIntOrNull() ?: -1
}

fun obterConta(): ContaBancaria {
    print("Digite o número da conta: ")
    val n = readln().toInt()
    return ContaBancaria.buscarConta(n) ?: throw IllegalArgumentException("Conta não encontrada.")
}

fun teste(){
    val conta1 = ContaBancaria.buscarConta(101) ?: throw IllegalArgumentException("Conta não encontrada!")
    val conta2 = ContaBancaria.buscarConta(102) ?: throw IllegalArgumentException("Conta não encontrada!")

    conta1.sacar(10.0)
    println("Saque realizado com sucesso. Novo saldo: R$ ${conta1.consultarSaldo()}")

    conta2.depositar(10.0)
    println("Depósito realizado com sucesso. Novo saldo: R$ ${conta2.consultarSaldo()}")

    println("Saldo da conta ${conta1.numero}: R$ ${conta1.consultarSaldo()}")
    println("Saldo da conta ${conta2.numero}: R$ ${conta2.consultarSaldo()}")

}