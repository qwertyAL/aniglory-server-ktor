package ru.aniglory

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*
import ru.aniglory.plugins.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting(userUseCase)
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }
}
