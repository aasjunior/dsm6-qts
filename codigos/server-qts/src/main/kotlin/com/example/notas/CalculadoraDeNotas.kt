package com.example.notas

import java.util.concurrent.ConcurrentHashMap

// Mapa para armazenar as notas em tempo de execução
val notas = ConcurrentHashMap<String, List<Double>>()

data class CalculadoraDeNotas(val nomeAluno: String, var notas: List<Double>) {

    // Método para calcular a média das notas
    fun calcularMedia(): String {
        if (notas.isEmpty()) {
            return "Ainda não há notas para calcular a média."
        }
        val media = notas.average()
        return "A média das notas de $nomeAluno é: $media"
    }

    // Método para encontrar a maior nota
    fun encontrarMaiorNota(): String {
        if (notas.isEmpty()) {
            return "Ainda não há notas para encontrar a maior."
        }
        val maiorNota = notas.maxOrNull() ?: 0.0
        return "A maior nota de $nomeAluno é: $maiorNota"
    }

    // Método para encontrar a menor nota
    fun encontrarMenorNota(): String {
        if (notas.isEmpty()) {
            return "Ainda não há notas para encontrar a menor."
        }
        val menorNota = notas.minOrNull() ?: 10.0
        return "A menor nota de $nomeAluno é: $menorNota"
    }
}