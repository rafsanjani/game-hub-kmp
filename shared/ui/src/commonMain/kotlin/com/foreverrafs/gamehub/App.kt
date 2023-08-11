package com.foreverrafs.gamehub

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.foreverrafs.gamehub.screens.GameListScreen


@Composable
fun App() {
    val platform = getPlatform()

    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme())
            darkColorScheme()
        else lightColorScheme()
    ) {
        CompositionLocalProvider(LocalPlatform provides platform) {
            Surface {
                AppContainer {
                    Navigator(GameListScreen) {
                        SlideTransition(it)
                    }
                }
            }
        }
    }
}

/**
 * We don't have responsive screens for web so we shrink items to 50% of
 * the screen width
 */
@Composable
private fun AppContainer(
    content: @Composable () -> Unit
) {
    if (LocalPlatform.current == Platform.Web) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
            ) {
                content()
            }
        }
    } else {
        content()
    }
}

val LocalPlatform = compositionLocalOf<Platform> { error("No Platform defined!") }