package ru.aniglory.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.aniglory.cashe.InMemoryCashe
import ru.aniglory.cashe.TokenCashe
import java.util.*

class LoginController(private val call: ApplicationCall) {
    suspend fun performLogin() {
        
        val first = InMemoryCashe.userList.firstOrNull { it.login == receive.login }

        if(first == null) {
            call.respond(HttpStatusCode.BadRequest, "User no found")
        } else {
            if(first.password == receive.password) {
                val token = UUID.randomUUID().toString()
                InMemoryCashe.token.add(TokenCashe(login = receive.login, token = token))
                call.respond(LoginResponseRemote(token = token))
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }
}