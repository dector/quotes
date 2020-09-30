plugins {
    kotlin("multiplatform") version Kotlin.version
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":quotes-domain"))
                implementation(project(":quotes-common"))
                implementation(project(":quotes-repositories"))
            }
        }

        val jvmMain by getting {}
    }
}

/*dependencies {
    testImplementation("org.testng:testng:${Versions.testng}")
}

tasks.withType<Test>().all {
    useTestNG()
}*/
