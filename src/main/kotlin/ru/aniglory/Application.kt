package ru.aniglory

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.aniglory.auentification.JwtService
import ru.aniglory.data.repository.UserRepositoryImpl
import ru.aniglory.domain.usecase.UserUseCase
import ru.aniglory.plugins.*
import ru.aniglory.plugins.DatabaseFactory.initializationDatabase

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {

    val jwtService = JwtService()
    val repository = UserRepositoryImpl()
    val userUseCase = UserUseCase(repository, jwtService)

    initializationDatabase()
    configureMonitoring()
    configureSerialization()
    configureSecurity(userUseCase)
    configureRouting(userUseCase)
}