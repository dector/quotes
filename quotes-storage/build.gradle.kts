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
            }
        }

        val jvmMain by getting {}
    }
}

tasks.withType<Test>().all {
    useJUnitPlatform()
}
