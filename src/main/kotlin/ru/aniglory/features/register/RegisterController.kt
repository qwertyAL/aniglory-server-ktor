package ru.aniglory.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.aniglory.cashe.InMemoryCashe
import ru.aniglory.cashe.TokenCashe
import ru.aniglory.database.tokens.TokenDTO
import ru.aniglory.database.tokens.Tokens
import ru.aniglory.database.users.UserDTO
import ru.aniglory.database.users.Users
import ru.aniglory.utils.isValidEmail
import java.util.*

class RegisterController(private val call: ApplicationCall) {
    suspend fun registerNewUser() {
        val receive = call.receive<RegisterReceiveRemote>()
        if(!receive.email.isValidEmail()) {
            call.respond(HttpStatusCode.BadRequest, "email is not valid")
        }

        val userDTO = Users.fetchUser(receive.email)

        if(userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            val token = UUID.randomUUID().toString()
            Users.insert(
                UserDTO(
                    username = receive.username,
                    email = receive.email,
                    password = receive.password
                )
            )

            Tokens.insert(TokenDTO(
                email = receive.email,
                token = token
                )
            )

            call.respond(RegisterResponseRemote(token = token))
        }
    }
}