package com.aasjunior.model

import java.sql.Connection
import java.sql.DriverManager

object Products {
    private const val URL = "jdbc:h2:mem:lojaPRO;DB_CLOSE_DELAY=-1"
    private const val USER = "aasjunior"
    private const val PASSWORD = ""

    fun connect(): Connection{
        return DriverManager.getConnection(URL, USER, PASSWORD)
    }

    fun initialize(){
        connect().use { connection ->
            connection.createStatement().use { statement ->
                statement.execute(
                    """
                        CREATE TABLE IF NOT EXISTS products(
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name TEXT NOT NULL,
                            price DOUBLE NOT NULL,
                            stock INT NOT NULL DEFAULT 0
                        );
                        
                        CREATE TABLE IF NOT EXISTS shopping_cart(
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            productID INT NOT NULL,
                            amount INT NOT NULL,
                            totalValue DOUBLE NOT NULL,
                            FOREIGN KEY (productID) REFERENCES products(id) ON DELETE CASCADE
                        );
                    """.trimIndent()
                )
            }
        }
    }

    fun populateData(){
        connect().use { connection ->
            connection.createStatement().use { statement ->
                statement.execute(
                    """
                        MERGE INTO products(id, name, price, stock)
                        VALUES (1, 'Camiseta', 29.9, 10), (2, 'Calça', 59.9, 10)
                    """.trimIndent()
                )
            }
        }
    }

    fun addProduct(product: Product){
        connect().use { connection ->
            connection.prepareStatement(
                "INSERT INTO products(name, price, stock) VALUES(?, ?, ?)"
            ).use { preparedStatement ->
                preparedStatement.setString(1, product.name)
                preparedStatement.setDouble(2, product.price)
                preparedStatement.setInt(3, product.stock)
                preparedStatement.executeUpdate()
            }
        }
    }

    fun getProductById(id: Int): Product? {
        connect().use { connection ->
            connection.prepareStatement("SELECT * FROM products WHERE id = ?").use { preparedStatement ->
                preparedStatement.setInt(1, id)
                val resultSet = preparedStatement.executeQuery()
                return if (resultSet.next()) {
                    Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock")
                    )
                } else {
                    null
                }
            }
        }
    }

    fun listProducts(): List<Product>{
        val products = mutableListOf<Product>()
        connect().use { connection ->
            connection.createStatement().use { statement ->
                val resultSet = statement.executeQuery("SELECT * FROM products")
                while(resultSet.next()){
                    products.add(Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock")
                    ))
                }
            }
        }

        return products
    }

    fun updateStock(productID: Int, newStock: Int){
        connect().use { connection ->
            connection.prepareStatement(
                "UPDATE products SET stock = ? WHERE id = ?;"
            ).use { preparedStatement ->
                preparedStatement.setInt(1, newStock)
                preparedStatement.setInt(2, productID)
                preparedStatement.executeUpdate()
            }
        }
    }
}