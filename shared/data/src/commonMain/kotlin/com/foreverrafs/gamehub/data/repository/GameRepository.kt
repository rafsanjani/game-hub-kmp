package com.foreverrafs.gamehub.data.repository

import com.foreverrafs.gamehub.data.Result
import com.foreverrafs.gamehub.data.dto.GameResponse
import com.foreverrafs.gamehub.data.dto.toGame
import com.foreverrafs.gamehub.model.Game
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class GameRepository {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(json = Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    private var cachedGames: List<Game>? = null

    suspend fun getGames(): Result {
        if (cachedGames != null) {
            return Result.Success(cachedGames!!)
        }

        val request = client.get(url = Url("$BASE_URL/games")) {
            parameter("key", API_KEY)
        }

        if (request.status != HttpStatusCode.OK) {
            return Result.Error(Exception(request.bodyAsText()))
        }

        val response = request.body<GameResponse>()

        return Result.Success(data = response.results.map { it.toGame() })
    }

    companion object {
        const val BASE_URL = "https://api.rawg.io/api"
        const val API_KEY = "15798556b47e47188a81f00273c70d4a"
    }
}