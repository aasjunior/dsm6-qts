package com.aasjunior

fun main() {
    // Lista de notas dos alunos
    val notas = listOf(7.5, 8.0, 6.0, 9.0, 7.0, 5.5, 8.5)

    // Calcular a média das notas usando map e reduce
    val totalNotas = notas.size
    val somaNotas = notas.reduce { acc, nota -> acc + nota }
    val media = somaNotas / totalNotas

    // Encontrar a nota máxima usando reduce
    val notaMaxima = notas.reduce { acc, nota -> if (nota > acc) nota else acc }

    // Encontrar a nota mínima usando reduce
    val notaMinima = notas.reduce { acc, nota -> if (nota < acc) nota else acc }

    // Exibir os resultados
    println("Média das notas: $media")
    println("Nota máxima: $notaMaxima")
    println("Nota mínima: $notaMinima")
}