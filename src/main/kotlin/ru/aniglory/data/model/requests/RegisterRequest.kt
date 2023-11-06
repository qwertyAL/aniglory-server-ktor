package ru.aniglory.data.model.requests

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val email: String,
    val username: String,
    val password: String,
    val isActive: Boolean = false
)
