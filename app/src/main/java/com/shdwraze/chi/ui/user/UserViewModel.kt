package com.shdwraze.chi.ui.user

import androidx.lifecycle.ViewModel
import com.shdwraze.chi.data.DataSource
import com.shdwraze.chi.data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UserUiState())

    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun getAllUsers(): List<User> {
        return DataSource.users
    }

    fun loadData() {
        _uiState.value = UserUiState(users = getAllUsers())
    }

    fun updateUserState(userId: Int, isStudent: Boolean) {
        val updatedUser = _uiState.value.users[userId].copy(isStudent = isStudent)
        println(updatedUser.isStudent)

        _uiState.update { currentState ->
            val users = currentState.users.toMutableList()
            users[userId] = updatedUser
            println(updatedUser)

            currentState.copy(
                users = users
            )
        }
    }
}