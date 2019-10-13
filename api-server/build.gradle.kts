plugins {
    kotlin("jvm") version Versions.kotlin
    application
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation(project(":domain"))
    implementation("com.beust:klaxon:${Versions.klaxon}")
    implementation("com.sparkjava:spark-core:2.3")

    testImplementation("org.testng:testng:${Versions.testng}")
}

tasks.withType<Test>().all {
    useTestNG()
}

application {
    mainClassName = "io.github.dector.quote.api_server.MainKt"
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
