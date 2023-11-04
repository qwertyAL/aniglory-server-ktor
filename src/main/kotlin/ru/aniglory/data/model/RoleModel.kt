package ru.aniglory.data.model

import ru.aniglory.utils.Constants

enum class RoleModel {
    DEVELOPER, TESTER, ADMIN, MODERATOR, PRO_USER, USER
}

fun String.getRoleByString(): RoleModel {
    return when(this) {
        Constants.Role.DEVELOPER -> RoleModel.DEVELOPER
        Constants.Role.TESTER -> RoleModel.TESTER
        Constants.Role.ADMIN -> RoleModel.ADMIN
        Constants.Role.MODERATOR -> RoleModel.MODERATOR
        Constants.Role.PRO_USER -> RoleModel.PRO_USER
        else -> RoleModel.USER
    }
}

fun RoleModel.getStringByRole() : String {
    return when(this) {
        RoleModel.DEVELOPER -> Constants.Role.DEVELOPER
        RoleModel.TESTER -> Constants.Role.TESTER
        RoleModel.ADMIN -> Constants.Role.ADMIN
        RoleModel.MODERATOR -> Constants.Role.MODERATOR
        RoleModel.PRO_USER -> Constants.Role.PRO_USER
        else -> Constants.Role.DEVELOPER
    }
}