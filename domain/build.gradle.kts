plugins {
    kotlin("multiplatform") version Kotlin.version
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    sourceSets {
        val commonMain by getting
        val jvmMain by getting
    }
}
