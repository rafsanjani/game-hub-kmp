package com.foreverrafs.gamehub.data.dto

import com.foreverrafs.gamehub.model.Game
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class GameDto(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("background_image")
    val backgroundImage: String,
    @SerialName("released")
    val released: LocalDate,
)

@Serializable
internal data class GameResponse(
    @SerialName("count")
    val count: Int,
    @SerialName("next")
    val next: String,
    @SerialName("previous")
    val previous: String?,
    @SerialName("results")
    val results: List<GameDto>,
)

internal fun GameDto.toGame() =
    Game(id = id, name = name, backgroundImage = backgroundImage, released = released)
