package ru.aniglory.domain.usecase

import com.auth0.jwt.JWTVerifier
import ru.aniglory.auentification.JwtService
import ru.aniglory.data.model.UserModel
import ru.aniglory.domain.repository.UserRepository

class UserUseCase(
    private val repositoryImpl: UserRepository,
    private val jwtService: JwtService
) {

    suspend fun createUser(userModel: UserModel) = repositoryImpl.insertUser(userModel = userModel)

    suspend fun findUserByEmail(email: String) = repositoryImpl.getUserByEmail(email = email)

    fun generateToken(userModel: UserModel): String = jwtService.generateToken(user = userModel)

    fun getJwtVerifier(): JWTVerifier = jwtService.getVerifier()

}