package com.foreverrafs.gamehub.data.dto

import com.foreverrafs.gamehub.model.Game
import com.foreverrafs.gamehub.model.GameGenre
import com.foreverrafs.gamehub.model.GamePlatform
import com.foreverrafs.gamehub.model.GameImage
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

    @SerialName("metacritic")
    val rating: Int,

    @SerialName("genres")
    val genres: List<GenreDto>,

    @SerialName("parent_platforms")
    val platforms: List<GamePlatformResponseDto>,

    @SerialName("short_screenshots")
    val screenshots: List<GameScreenshotsDto>,
)


@Serializable
internal data class GenreDto(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String
)

@Serializable
internal data class GamePlatformResponseDto(
    @SerialName("platform")
    val platform: GamePlatformDto
)

@Serializable
internal data class GamePlatformDto(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String
)

@Serializable
internal data class GameScreenshotsDto(
    @SerialName("id")
    val id: String,

    @SerialName("image")
    val image: String
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
    Game(
        id = id,
        name = name,
        backgroundImage = getCroppedImageUrl(backgroundImage),
        released = released,
        rating = rating,
        genres = genres.map { GameGenre(id = it.id, name = it.name) },
        platforms = platforms.map { GamePlatform(id = it.platform.id, name = it.platform.name) },
        screenshots = screenshots.map {
            GameImage(
                id = it.id,
                image = getCroppedImageUrl(it.image)
            )
        },
    )

private fun getCroppedImageUrl(imageUrl: String): String {
    return imageUrl.replace("media/", "media/crop/600/400/")
}