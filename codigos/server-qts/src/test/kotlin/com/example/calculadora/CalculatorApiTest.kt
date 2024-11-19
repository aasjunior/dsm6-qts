package com.example.calculadora

import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class CalculatorApiTest {
    @Test
    fun `test sum endpoint`() = testApplication {
        application { CalculatorAPI() }

        client.get("/sum/10/5").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("15.0", bodyAsText())
        }
    }

    @Test
    fun `test sub endpoint`() = testApplication {
        application { CalculatorAPI() }

        client.get("/sub/10/5").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("5.0", bodyAsText())
        }
    }

    @Test
    fun `test multiplication endpoint`() = testApplication {
        application { CalculatorAPI() }

        client.get("/mult/10/5").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("50.0", bodyAsText())
        }
    }

    @Test
    fun `test division endpoint`() = testApplication {
        application { CalculatorAPI() }

        client.get("/div/10/2").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("5.0", bodyAsText())
        }
    }

    @Test
    fun `test division by zero`() = testApplication {
        application { CalculatorAPI() }

        client.get("/div/10/0").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Divisão por zero não é permitida!", bodyAsText())
        }
    }
}