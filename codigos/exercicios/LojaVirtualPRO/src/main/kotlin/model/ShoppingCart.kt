package com.aasjunior.model

class ShoppingCart {
    private val list: MutableMap<Int, Item> = mutableMapOf()

    fun addItem(item: Item, quantity: Int) {
        if(quantity > item.stock){
            throw IllegalArgumentException("Quantidade em estoque insuficiente para ${item.name}")
        }

        val updateItem = item.copy(quantity = quantity)
        list[item.id] = updateItem
        Products.updateStock(item.id, item.stock - quantity)
        println("${item.name} adicionado ao carrinho.")
    }

    fun removeItem(id: Int) {
        val item = list[id]
        if(item != null){
            list.remove(id)
            val product = Products.getProductById(item.id)

            Products.updateStock(item.id, product!!.stock + item.quantity)
            println("${item.name} removido do carrinho.")
        }else{
            println("Produto não encontrado no carrinho")
        }
    }

    fun changeQuantity(id: Int, newQuantity: Int) {
        val item = list[id]
        if(item != null){

            val quantityDifference = newQuantity - item.quantity

            if(newQuantity > item.stock + item.quantity){
                println("Quantidade em estoque insuficiente.")
            }else{
                val product = Products.getProductById(item.id)
                if (product != null) {
                    // Atualiza o estoque somando ou subtraindo a diferença da quantidade
                    Products.updateStock(item.id, product.stock - quantityDifference)

                    // Atualiza a quantidade no carrinho
                    list[id] = item.copy(quantity = newQuantity)
                    println("Quantidade de ${item.name} alterada para $newQuantity.")
                } else {
                    println("Produto não encontrado no banco de dados.")
                }
            }
        }else{
            println("Produto não encontrado no carrinho.")
        }
    }

    fun calculateTotal(): Double {
        return list.values.sumOf { it.price * it.quantity }
    }

    fun displayItems() {
        if(list.isEmpty()){
            throw IllegalArgumentException("O carrinho está vazio.")
        }else{
            list.forEach { (id, item) ->
                println("Produto: ${item.name}, Valor: R$ ${item.price}, Quantidade: ${item.quantity}")
            }
        }
    }

    fun finalizePurchase(){
        if(list.isEmpty()){
            throw IllegalArgumentException("Carrinho vazio.")
        }else{
            val total = calculateTotal()
            println("\nValor total da compra: R$ $total \nCompra finalizada com sucesso!")
            list.clear()
        }
    }
}
