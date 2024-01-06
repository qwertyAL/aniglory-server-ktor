package ru.aniglory.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens : Table() {
    private val id = Tokens.integer("id")
    private val email = Tokens.varchar("email", 25)
    private val token = Tokens.varchar("token", 50)

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            Tokens.insert {
                it[email] = tokenDTO.email
                it[token] = tokenDTO.token
            }
        }
    }
}