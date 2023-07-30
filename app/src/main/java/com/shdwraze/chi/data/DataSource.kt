package com.shdwraze.chi.data

import com.shdwraze.chi.data.model.User

object DataSource {
    val users = listOf(
        User(1,"Helen", 42),
        User(2,"Vladyslav", 20, true),
        User(3,"Yaroslava", 20, true),
        User(4,"Vitalii", 32),
        User(5,"Maya", 19, true),
        User(6,"Jack", 38, true),
        User(7,"Dylan", 35),
    )
}