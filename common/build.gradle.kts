plugins {
    kotlin("jvm") version Versions.kotlin
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation(project(":domain"))
    implementation("com.eclipsesource.minimal-json:minimal-json:0.9.4")

    testImplementation("org.testng:testng:${Versions.testng}")
}

tasks.withType<Test>().all {
    useTestNG()
}
