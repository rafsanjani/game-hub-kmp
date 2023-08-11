package com.foreverrafs.gamehub

enum class Platform {
    Android,
    Apple,
    Desktop,
    Web;
}

expect fun getPlatform(): Platform