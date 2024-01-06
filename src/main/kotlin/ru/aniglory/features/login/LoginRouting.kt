package ru.aniglory.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.aniglory.cashe.InMemoryCashe
import ru.aniglory.cashe.TokenCashe
import java.util.UUID

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            val loginController = LoginController(call)
        }
    }
}
