plugins {
    kotlin("jvm")
    application
}

dependencies {
    implementation(project(":quotes-domain"))

    implementation(Ktor.server.core)
    implementation(Ktor.server.cio)
    implementation(Ktor.serialization)
    implementation(Logback.classic)
}

application {
    mainClassName = "space.dector.quotes.server.api.Main"
}
