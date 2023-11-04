package ru.aniglory.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import ru.aniglory.auentification.JwtService
import ru.aniglory.data.model.RoleModel
import ru.aniglory.data.model.UserModel
import ru.aniglory.data.repository.UserRepositoryImpl
import ru.aniglory.domain.usecase.UserUseCase

fun Application.configureSecurity(userUseCase: UserUseCase) {

    authentication {
        jwt("jwt") {
            verifier(userUseCase.getJwtVerifier())
            realm = "Service server" //maybe change
            validate {
                val payload = it.payload
                val email = payload.getClaim("email").asString()
                val user = userUseCase.findUserByEmail(email = email)
                user
            }
        }
    }
}
