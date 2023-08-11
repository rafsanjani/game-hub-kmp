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
                implementation(libs.androidx.activity.compose)
                implementation(libs.appcompat)
                implementation(libs.material)
                implementation (libs.androidx.activity.ktx)
                implementation(projects.shared.ui)
                implementation(libs.androidx.core.ktx.v190)
            }
        }
    }
}

android {
    compileSdk = 34
    defaultConfig {
        applicationId = "com.foreverrafs.gamehub"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    namespace = "com.foreverrafs.gamehub"

    packaging {
        resources.excludes.add(
            "META-INF/versions/9/previous-compilation-data.bin"
        )
    }
}
