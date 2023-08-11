package com.foreverrafs

import android.app.Application
import android.util.Log
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(firstModule())
        }
    }
}

class ClassA(val b: ClassB, val c: ClassC)
class ClassB()
class ClassC()

fun firstModule() = module {
    factory<Car> { Toyota() }

    single {
        ClassB()
    }

    single<String>(named("name")) {
        "Hello Rafsanjani"
    }

    single<String>(named("age")) {
        "21 years old"
    }

    single {
        ClassA(get(), get())
    }
}

interface Car {
    fun drive()
}

class Toyota : Car {
    override fun drive() {
        Log.d("Rafs", "drive: Toyota ")
    }
}
