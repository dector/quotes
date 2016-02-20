package io.github.dector.quote.api_server

import org.wasabi.app.AppServer

fun main(args: Array<String>) {
    val server = AppServer()

    server.get("/quotes", { response.send("Hello, fucking world!") })

    server.configuration.port = 1304
    server.start()
}
