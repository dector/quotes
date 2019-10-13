plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()

    sourceSets {
        getByName("commonMain") {
            dependencies {
                implementation(kotlin("stdlib-common"))

                implementation(project(":domain"))
                implementation(project(":common"))
            }
        }

        jvm().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        jvm().compilations["test"].defaultSourceSet {
            dependencies {
                implementation(Deps.kotlin_test)
            }
        }
    }
}

tasks.withType<Test>().all {
    useJUnitPlatform()
}
