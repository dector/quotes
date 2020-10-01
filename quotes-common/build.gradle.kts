plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":quotes-domain"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("com.eclipsesource.minimal-json:minimal-json:0.9.4")
            }
        }
    }
}

/*dependencies {
    implementation(project(":quotes-domain"))

    testImplementation("org.testng:testng:${Versions.testng}")
}

tasks.withType<Test>().all {
    useTestNG()
}*/
