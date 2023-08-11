import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

kotlin {
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(projects.shared.data)
    implementation(projects.shared.ui)
}

compose.desktop {
    application {
        mainClass = "com.rssreader.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "RssReader"
            packageVersion = "1.0.0"

            val iconsRoot = project.file("../common/src/desktopMain/resources/images")
            macOS {
                iconFile.set(iconsRoot.resolve("icon-mac.icns"))
            }

            windows {
                iconFile.set(iconsRoot.resolve("icon-windows.ico"))
                menuGroup = "RssReader"
                upgradeUuid = "18159995-d967-4CD2-8885-77BFA97CFA9F"
            }
            linux {
                iconFile.set(iconsRoot.resolve("icon-linux.png"))
            }
        }
    }
}

//compose {
//    kotlinCompilerPlugin.set("1.5.0")
//}
