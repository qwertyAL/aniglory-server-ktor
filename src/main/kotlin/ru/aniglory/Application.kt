package ru.aniglory

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database
import ru.aniglory.features.login.configureLoginRouting
import ru.aniglory.features.register.configureRegisterRouting
import ru.aniglory.plugins.*

fun main() {
    Database.connect("jdbc:postgresql://localhost:5434/playzone", driver = "org.postgresql.Driver", password = "&232717&Al")

    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configureSerialization()
}
