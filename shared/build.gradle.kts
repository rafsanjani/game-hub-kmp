@file:Suppress("UNUSED_VARIABLE")

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("native.cocoapods")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    js(IR) {
        browser()
        binaries.executable()
    }

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    cocoapods {
        summary = "Game Hub multiplatform library"
        homepage = "www.foreverrafs.com"
        version = "1.0"
        ios.deploymentTarget = "15.2"
        podfile = project.file("../iosApp/Podfile")

        framework {
            baseName = "shared"
            export(projects.shared.data)
            export(projects.shared.ui)
            isStatic = true

            binaryOption("bundleId", "com.foreverrafs.gamehub.shared")
        }
    }

    ios()
    jvm()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                api(projects.shared.data)
                api(projects.shared.ui)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }
}

android {
    namespace = "com.foreverrafs.gamehub"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
}
