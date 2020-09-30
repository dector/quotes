import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Kotlin.version
    application
}

dependencies {
    implementation(project(":quotes-domain"))
    implementation("com.beust:klaxon:${Versions.klaxon}")

    implementation(Ktor.server.core)
    implementation(Ktor.server.cio)
    implementation(Logback.classic)

    testImplementation("org.testng:testng:${Versions.testng}")
}

tasks.withType<Test>().all {
    useTestNG()
}

application {
    mainClassName = "io.github.dector.quote.api_server.MainKt"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
}

/*jar {
    manifest {
        attributes "Main-Class": project.ext.main_class
    }

    from {
        configurations.compile.collect { it.isDirectory() ? it : zipTree(it) }
    }

    version project.ext.server_version
}*/
