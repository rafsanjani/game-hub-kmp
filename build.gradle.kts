plugins {
    val compose = "1.5.0-dev1136"
    val agp = "8.1.0"
    val kotlin = "1.9.0"

    // trick: for the same plugin versions in all sub-modules
    id("com.android.application").version(agp).apply(false)
    id("com.android.library").version(agp).apply(false)
    id("org.jetbrains.compose").version(compose).apply(false)
    id("org.jetbrains.kotlin.plugin.serialization").version(kotlin).apply(false)
    kotlin("android").version(kotlin).apply(false)
    kotlin("multiplatform").version(kotlin).apply(false)
}
