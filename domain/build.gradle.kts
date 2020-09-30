plugins {
    kotlin("multiplatform") version Kotlin.version
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
}
