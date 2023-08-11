plugins {
    val compose = "1.5.1"
    val agp = "8.1.1"
    val kotlin = "1.9.10"

    // trick: for the same plugin versions in all sub-modules
    id("com.android.application").version(agp).apply(false)
    id("com.android.library").version(agp).apply(false)
    id("org.jetbrains.compose").version(compose).apply(false)
    id("org.jetbrains.kotlin.plugin.serialization").version(kotlin).apply(false)
    kotlin("android").version(kotlin).apply(false)
    kotlin("multiplatform").version(kotlin).apply(false)
}
