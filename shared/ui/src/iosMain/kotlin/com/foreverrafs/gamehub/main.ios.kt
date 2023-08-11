@file:Suppress("unused", "FunctionName")

package com.foreverrafs.gamehub

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.window.ComposeUIViewController

object ViewControllers {
    val mainViewController = ComposeUIViewController { App() }
    fun sampleButtonController(text: String, onClick: () -> Unit) = ComposeUIViewController {
        Button(
            onClick = onClick,
        ) {
            Text(text = text)
        }
    }
}

