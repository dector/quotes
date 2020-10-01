plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":quotes-domain"))
                implementation(project(":quotes-common"))
                implementation(project(":quotes-storage"))
            }
        }

        val jvmMain by getting {}
    }
}
