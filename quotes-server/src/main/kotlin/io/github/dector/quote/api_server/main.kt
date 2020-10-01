package io.github.dector.quote.api_server

import io.github.dector.quote.api_server.v1.sendAllQuotes
import io.ktor.application.call
import io.ktor.http.content.resource
import io.ktor.http.content.static
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.util.KtorExperimentalAPI


private const val ROOT_REDIRECT = "https://github.com/dector/quotes"
private const val DEFAULT_PORT = 8080

@OptIn(KtorExperimentalAPI::class)
fun main() {
    embeddedServer(
        CIO,
        port = detectPort(),
    ) {
        routing {
            static("/static") { resource("static") }

            get("/") { call.respondRedirect(ROOT_REDIRECT) }
            get("/api/v1/quotes") { call.sendAllQuotes() }
        }
    }.start(wait = true)
}

private fun detectPort(
    defaultPort: Int = DEFAULT_PORT
): Int = System
    .getenv("PORT")
    ?.toIntOrNull()
    ?: defaultPort
