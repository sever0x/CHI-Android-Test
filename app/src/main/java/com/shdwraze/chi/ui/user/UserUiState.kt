package com.shdwraze.chi.ui.user

import com.shdwraze.chi.data.model.User

data class UserUiState(
    val users: List<User> = listOf()
)