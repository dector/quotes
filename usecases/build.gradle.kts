plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation(project(":common"))
    implementation(project(":domain"))
    implementation(project(":storage"))

    testImplementation("org.testng:testng:${Versions.testng}")
}

tasks.withType<Test>().all {
    useTestNG()
}