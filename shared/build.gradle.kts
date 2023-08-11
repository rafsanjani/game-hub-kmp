plugins {
    kotlin("multiplatform")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            export(projects.shared.data)
            export(projects.shared.ui)

            binaryOption("bundleId", "com.foreverrafs.gamehub.shared")
        }
    }


    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.shared.data)
                api(projects.shared.ui)
            }
        }
    }
}

