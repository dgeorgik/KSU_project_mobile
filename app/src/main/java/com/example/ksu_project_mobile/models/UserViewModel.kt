package com.example.ksu_project_mobile.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class UserViewModel : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> get() = _userName


    private val _userRole = MutableLiveData<String>()
    val userRole: LiveData<String> get() = _userRole

    private val adminUser = User(
        name = "Админ Админович",
        email = "admin@email.com",
        password = "admin",
        role = "admin"
    )

    init {
        _users.value = mutableListOf()
        addUser(adminUser)
        updateUserRole(adminUser, "Администратор")
        _userRole.value = adminUser.role
    }

    fun setUserName(name: String) {
        _userName.value = name
    }

    fun addUser(user: User) {
        val updatedList = _users.value?.toMutableList() ?: mutableListOf()
        updatedList.add(user)
        _users.value = updatedList
    }
    fun setUserRole(role: String) {
        _userRole.value = role
    }

    fun updateUserRole(user: User, newRole: String) {
        val updatedList = _users.value?.map {
            if (it.email == user.email) it.copy(role = newRole) else it
        }
        _users.value = updatedList
    }

    fun authenticateUser(email: String, password: String): User? {
        val user = _users.value?.find {  it.email == email && it.password == password }
        _userRole.value = user?.role
        return _users.value?.find { it.email == email && it.password == password }
    }

}