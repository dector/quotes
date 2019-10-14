object Deps {
    const val kotlinx_coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinx_coroutines}"
    const val kotlinx_coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinx_coroutines}"

    const val kotlin_test = "io.kotlintest:kotlintest-runner-junit5:${Versions.kotlin_test}"
}

object Versions {
    const val kotlin = "1.3.50"
    const val kotlin_test = "3.4.2"
    const val kotlinx_coroutines = "1.3.2"

    const val klaxon = "0.24"
    const val testng = "6.8"
}

object Android {
    const val version_code = 1
    const val version_name = "1.0"

    const val min_sdk = 15
    const val target_sdk = 29
    const val compile_sdk = 29
}

object Server {
    const val api_version = "1.0"
    const val server = "1.0.1"
}
