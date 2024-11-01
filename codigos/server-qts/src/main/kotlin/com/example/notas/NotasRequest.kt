package com.example.notas

import kotlinx.serialization.Serializable

@Serializable
data class NotasRequest(val aluno: String, val notas: List<Double>)
