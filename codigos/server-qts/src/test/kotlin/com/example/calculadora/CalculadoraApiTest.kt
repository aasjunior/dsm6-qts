package com.example.calculadora

import com.example.plugins.configureRouting
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class CalculadoraApiTest {
    @Test
    fun `test sum route`() = testApplication {
        application {
            configureRouting()
        }

        client.get(urlString = "sum/5/3").apply {
            assertEquals(expected = HttpStatusCode.OK, actual = status)
            assertEquals(expected = "8.0", actual = bodyAsText())
        }
    }
}