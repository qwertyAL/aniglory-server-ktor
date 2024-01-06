package ru.aniglory.cashe

import ru.aniglory.features.register.RegisterReceiveRemote

data class TokenCashe (
    val login: String,
    val token: String
)

object InMemoryCashe {
    val userList: MutableList<RegisterReceiveRemote> = mutableListOf()
    val token: MutableList<TokenCashe> = mutableListOf()
}