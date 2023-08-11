package com.foreverrafs

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MainActivity : ComponentActivity() {
    private val name: String by inject(qualifier = named("age"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("Rafs", "onCreate: $name")
    }
}