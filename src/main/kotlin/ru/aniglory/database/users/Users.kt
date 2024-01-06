package ru.aniglory.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.Exception

object Users: Table() {
    private val id = Users.integer("id")
    private val username = Users.varchar("username", 25)
    private val password = Users.varchar("password", 25)
    private val email = Users.varchar("email", 25)

    fun insert(userDTO: UserDTO) {
        transaction {
            Users.insert {
                it[username] = userDTO.username
                it[password] = userDTO.password
                it[email] = userDTO.email
            }
        }
    }

    fun fetchUser(email: String) : UserDTO? {
        return try {
            val userModel = Users.select { Users.email.eq(email) }.single()
            UserDTO(
                username = userModel[username],
                password = userModel[password],
                email = userModel[Users.email]
            )
        } catch (e: Exception) {
            null
        }
    }
}