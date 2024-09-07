package com.aasjunior.model


fun menu(){
    val shoppingCart = ShoppingCart()

    while(true){
        try {
            when(displayMenu()){
                0 -> {
                    teste(shoppingCart)
                }
                1 -> {
                    addProduct()
                }
                2 -> {
                    listProducts(shoppingCart)
                }
                3 -> {
                    editShoppingCart(shoppingCart)
                }
                4 -> {
                    println("Saindo...")
                    break
                }
                else -> println("Opção inválida.")
            }
        }catch(e: IllegalArgumentException) {
            println(e.message)
        }catch(e: Exception) {
            println("Erro inesperado: ${e.message}")
        }
        println()
    }
}

fun displayMenu(): Int{
    println("Escolha a operação:\n")
    println("0. Teste de Mesa")
    println("1. Novo Produto")
    println("2. Listar Produtos")
    println("3. Carrinho")
    println("4. Sair\n")
    print("Opção: ")
    return readln().toIntOrNull() ?: -1
}

fun addProduct(){
    print("\n\nNome do produto: ")
    val nome = readln()
    print("Preço do produto: ")
    val preco = readln().toDouble()
    print("Estoque inicial: ")
    val estoque = readln().toInt()

    val produto = Product(null, nome, preco, estoque)
    Products.addProduct(produto)

    println("$nome adicionado com sucesso!\n\n")
}

fun listProducts(shoppingCart: ShoppingCart){
    val products = Products.listProducts().filter { it.stock > 0 }

    if(products.isEmpty()){
        throw IllegalArgumentException("\n\nNenhum produto disponível!")
    }

    println("\n\nProdutos disponíveis:")
    products.forEachIndexed { index, product ->
        println("${index + 1}. ${product.name} - R$${product.price} (Estoque: ${product.stock})")
    }

    println("\nEscolha o produto pelo número (ou 0 para voltar): ")
    val option = readln().toIntOrNull() ?: throw IllegalArgumentException("Opção inválida.")

    if(option in 1..products.size){
        val productSelected = products[option - 1]
        print("Quantidade: ")
        val quantity = readln().toIntOrNull() ?: throw IllegalArgumentException("Quantidade inválida.")

        try{
            shoppingCart.addItem(
                Item(
                    productSelected.id!!,
                    productSelected.name,
                    productSelected.price,
                    productSelected.stock,
                    quantity
                ),
                quantity
            )
        }catch(e: Exception){
            println("Erro: ${e.message}")
            return
        }
    }
}

fun editShoppingCart(shoppingCart: ShoppingCart){
    while(true){
        println("\n\nCarrinho de compras:\n")
        shoppingCart.displayItems()

        println("\n1. Remover item")
        println("2. Alterar quantidade de item")
        println("3. Finalizar compra")
        println("4. Voltar\n")
        print("Escolha uma opção: ")
        when(readln().toIntOrNull()){
            1 -> {
                print("\n\nID do produto a remover: ")
                val id = readln().toIntOrNull() ?: continue
                shoppingCart.removeItem(id)
            }
            2 -> {
                print("\n\nID do produto a alterar quantidade: ")
                val id = readln().toIntOrNull() ?: continue
                print("Nova quantidade: ")
                val quantity = readln().toIntOrNull() ?: continue
                shoppingCart.changeQuantity(id, quantity)
            }
            3 -> {
                shoppingCart.finalizePurchase()
                break
            }
            4 -> break
            else -> println("Opção inválida.\n")
        }
    }
}

fun teste(shoppingCart: ShoppingCart){
    val products = Products.listProducts().filter { it.stock > 0 }

    println("Produtos disponíveis: ")
    products.forEachIndexed { index, product ->
        println("${index + 1}. ${product.name} - R$${product.price} (Estoque: ${product.stock})")
    }

    val product = Product(null, "Casaco", 80.0, 20)
    Products.addProduct(product)

    val productSelected = Products.getProductById(1) ?: throw IllegalArgumentException("Produto não encontrado.")
    shoppingCart.addItem(
        Item(
            productSelected.id!!,
            productSelected.name,
            productSelected.price,
            productSelected.stock,
            5
        ),
        5
    )

    shoppingCart.finalizePurchase()
}