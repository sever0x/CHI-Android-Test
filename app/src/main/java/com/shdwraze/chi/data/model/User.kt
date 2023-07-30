package com.shdwraze.chi.data.model

data class User(
    val id: Int = 0,
    val name: String,
    val age: Int,
    var isStudent: Boolean = false
)