package com.foreverrafs.gamehub.data

import com.foreverrafs.gamehub.model.Game

sealed interface Result {
    data class Success(val data: List<Game>) : Result
    data class Error(val error: Throwable) : Result
}