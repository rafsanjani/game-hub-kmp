package com.foreverrafs.gamehub.model

import kotlinx.datetime.LocalDate

data class Game(
    val id: String,
    val name: String,
    val backgroundImage: String,
    val released: LocalDate,
    val rating: Int,
    val genres: List<GameGenre>,
    val platforms: List<GamePlatform>,
    val screenshots: List<GameImage>,
)

data class GameGenre(
    val name: String,
    val id: String,
)

data class GamePlatform(
    val name: String,
    val id: String,
)

data class GameImage(
    val id: String,
    val image: String
)