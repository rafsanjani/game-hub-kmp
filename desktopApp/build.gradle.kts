import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

kotlin {
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(compose.material3)
    implementation(projects.shared.data)
    implementation(projects.shared.ui)
}

compose.desktop {
    application {
        mainClass = "com.foreverrafs.gamehub.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Deb, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Game Hub"
            packageVersion = "1.0.0"
        }
    }
}

