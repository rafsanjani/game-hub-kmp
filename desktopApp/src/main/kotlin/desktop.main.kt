package com.foreverrafs.gamehub

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(size = DpSize(900.dp, 1000.dp)),
        resizable = false,
        title = "Game Hub"
    ) {
        App()
    }
}

@Preview
@Composable
private fun Preview() {
   App()
}
