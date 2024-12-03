package com.example.ksu_project_mobile.models

import androidx.lifecycle.*
import com.example.ksu_project_mobile.fragments.UserEntity
import com.example.ksu_project_mobile.fragments.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _currentUser = MutableLiveData<UserEntity?>()
    val currentUser: LiveData<UserEntity?> get() = _currentUser

    val users: StateFlow<List<UserEntity>> = repository.users
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    suspend fun authenticateUser(email: String, password: String): UserEntity? {
        return repository.authenticateUser(email, password).also { user ->
            _currentUser.value = user
        }
    }

    fun addUser(user: UserEntity) {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

    fun updateUser(user: UserEntity) {
        viewModelScope.launch {
            repository.updateUser(user)
        }
    }

    fun initData() {
        viewModelScope.launch {
            repository.insertAdminIfNotExists()
        }
    }

    fun setUserRole(role: String) {
        _currentUser.value?.let { user ->
            val updatedUser = user.copy(role = role)
            updateUser(updatedUser)
        }
    }

    fun setUserNav(user: UserEntity) {
        _currentUser.value = user
    }

    fun setUserName(name: String) {
        _currentUser.value?.let { user ->
            val updatedUser = user.copy(name = name)
            updateUser(updatedUser)
        }
    }
    fun deleteUser(user: UserEntity) {
        viewModelScope.launch {
            repository.deleteUser(user)  // Удаление пользователя из репозитория
        }
    }

}
