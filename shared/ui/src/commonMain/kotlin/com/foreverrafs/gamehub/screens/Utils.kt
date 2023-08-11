package com.foreverrafs.gamehub.screens

import androidx.compose.runtime.Composable
import com.foreverrafs.gamehub.LocalPlatform
import com.foreverrafs.gamehub.Platform

@Composable
fun isMobileDevice(): Boolean {
    val platform = LocalPlatform.current
    return platform == Platform.Apple || platform == Platform.Android
}