package com.example.ksu_project_mobile.fragments

import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val users: Flow<List<UserEntity>> = userDao.getAllUsers()

    suspend fun insertUser(user: UserEntity) = userDao.insertUser(user)

    suspend fun authenticateUser(email: String, password: String): UserEntity? {
        return userDao.authenticateUser(email, password)
    }

    suspend fun deleteUser(user: UserEntity) = userDao.deleteUser(user)

    suspend fun updateUser(user: UserEntity) = userDao.updateUser(user)

    suspend fun insertAdminIfNotExists() {
        val adminUser = UserEntity(
            id = 0,
            name = "Админ Админович",
            email = "admin@email.com",
            password = "admin",
            role = "admin"
        )
         val existingAdmin = userDao.authenticateUser(adminUser.email!! , adminUser.password!!)
        if (existingAdmin == null) {
            insertUser(adminUser)
        }
    }
}
