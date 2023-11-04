package ru.aniglory.data.repository

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import ru.aniglory.data.model.UserModel
import ru.aniglory.data.model.getRoleByString
import ru.aniglory.data.model.getStringByRole
import ru.aniglory.data.model.tables.UserTable
import ru.aniglory.domain.repository.UserRepository
import ru.aniglory.plugins.DatabaseFactory.dbQuery

class UserRepositoryImpl : UserRepository{
    override suspend fun getUserByEmail(email: String): UserModel? {
        return dbQuery {
            UserTable.select { UserTable.email.eq(email) }
                .map { rowToUser(row = it) }
                .singleOrNull()
        }
    }

    override suspend fun insertUser(userModel: UserModel) {
        return dbQuery {
            UserTable.insert {table ->
                table[email] = userModel.email
                table[username] = userModel.username
                table[password] = userModel.password
                table[isActive] = userModel.isActive
                table[role] = userModel.role.getStringByRole()
            }
        }
    }

    private fun rowToUser(row: ResultRow?) : UserModel? {
        if(row == null) {
            return null
        }

        return UserModel(
            id = row[UserTable.id],
            email = row[UserTable.email],
            username = row[UserTable.username],
            password = row[UserTable.password],
            isActive = row[UserTable.isActive],
            role = row[UserTable.role].getRoleByString()
        )
    }
}