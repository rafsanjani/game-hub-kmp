pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
        maven("https://jitpack.io")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal()

        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
        maven("https://jitpack.io")
    }
}

rootProject.name = "game-hub"
include(":androidApp")
include(":shared:data")
include(":shared:ui")
include(":desktopApp")
include(":webApp")


enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
