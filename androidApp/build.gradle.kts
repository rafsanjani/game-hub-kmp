@file:Suppress("UNUSED_VARIABLE")

plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
}

kotlin {
    androidTarget()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(projects.shared.data)
                implementation(libs.androidx.activity.compose)
                implementation("androidx.fragment:fragment-ktx:1.6.1")
                implementation("io.insert-koin:koin-android:3.4.3")
                implementation("androidx.activity:activity-ktx:1.7.2")
                implementation(libs.appcompat)
                implementation(libs.androidx.core.ktx.v190)
            }
        }
    }
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "org.jetbrains.Imageviewer"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    namespace = "com.foreverrafs.gamehub"

}
