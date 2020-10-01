@file:JvmName("Main")

package space.dector.quotes.server.api

import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.serialization.json
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.util.KtorExperimentalAPI
import space.dector.quotes.server.detectPort


@OptIn(KtorExperimentalAPI::class)
fun main() {
    embeddedServer(
        CIO,
        port = detectPort(),
    ) {
        install(ContentNegotiation) {
            json()
        }
        installRootRedirect()
        installApiRouting()
    }.start(wait = true)
}
