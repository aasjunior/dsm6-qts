package com.example.notas

class Notas(private val notasAlunos: Map<String, List<Double>>) {

    fun calcularMedia(aluno: String): Double? {
        val notas = notasAlunos[aluno] ?: return null
        val totalNotas = notas.size
        val somaNotas = notas.reduce { acc, nota -> acc + nota }
        return somaNotas / totalNotas
    }

    fun encontrarNotaMaxima(aluno: String): Double? {
        val notas = notasAlunos[aluno] ?: return null
        return notas.reduce { acc, nota -> if (nota > acc) nota else acc }
    }

    fun encontrarNotaMinima(aluno: String): Double? {
        val notas = notasAlunos[aluno] ?: return null
        return notas.reduce { acc, nota -> if (nota < acc) nota else acc }
    }

    fun obterResultados(aluno: String): Map<String, Double>? {
        val media = calcularMedia(aluno) ?: return null
        val notaMaxima = encontrarNotaMaxima(aluno) ?: return null
        val notaMinima = encontrarNotaMinima(aluno) ?: return null
        return mapOf(
            "media" to media,
            "notaMaxima" to notaMaxima,
            "notaMinima" to notaMinima
        )
    }
}
