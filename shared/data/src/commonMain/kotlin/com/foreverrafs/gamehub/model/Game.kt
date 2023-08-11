package com.foreverrafs.gamehub.model

import kotlinx.datetime.LocalDate

data class Game(
    val id: String,
    val name: String,
    val backgroundImage: String,
    val released: LocalDate,
)
