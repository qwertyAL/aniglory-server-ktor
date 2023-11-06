package ru.aniglory.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.aniglory.domain.usecase.UserUseCase
import ru.aniglory.routes.UserRoute

fun Application.configureRouting(userUseCase: UserUseCase) {

    routing {
        UserRoute(userUseCase = userUseCase)
    }
}
