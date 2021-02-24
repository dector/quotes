@file:Suppress("MayBeConstant")


@Deprecated("Migrate to dependency-scoped namespaces")
object Deps {
    const val kotlinx_coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinx_coroutines}"
    const val kotlinx_coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinx_coroutines}"

    const val anvil = "co.trikita:anvil-sdk15:${Versions.anvil}"

    const val koin_android = "org.koin:koin-android:${Versions.koin}"

    // Testing

    const val kotlin_test = "io.kotlintest:kotlintest-runner-junit5:${Versions.kotlin_test}"
}

object Kotlin {
    val version = "1.4.30"
}

object Kotlinx {

    object serialization {
        val version = "1.0.0-RC2"

        val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:$version"
    }
}

object Ktor {
    val version = "1.4.1"

    val serialization = "io.ktor:ktor-serialization:$version"

    object server {
        val core = "io.ktor:ktor-server-core:$version"
        val cio = "io.ktor:ktor-server-cio:$version"
        val html = "io.ktor:ktor-html-builder:$version"
    }
}

object Logback {
    val version = "1.2.3"

    val classic = "ch.qos.logback:logback-classic:$version"
}

@Deprecated("Migrate to dependency-scoped namespaces")
object Versions {
    const val kotlin_test = "3.4.2"
    const val kotlinx_coroutines = "1.3.2"

    const val anvil = "0.5.0"
    const val koin = "2.0.1"

    const val klaxon = "0.24"
    const val testng = "6.8"
}
