plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version Kotlin.version
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(Kotlinx.serialization.json)
            }
        }
        val jvmMain by getting
    }
}
