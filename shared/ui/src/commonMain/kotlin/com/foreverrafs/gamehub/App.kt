package com.foreverrafs.gamehub

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.foreverrafs.gamehub.screens.GameListScreen
import com.foreverrafs.gamehub.transition.SlideTransition


@Composable
fun App() {
    Navigator(GameListScreen) {
        SlideTransition(it)
    }
}

