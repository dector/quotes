package io.github.dector.quote.api_server

import io.github.dector.quote.api_server.v1.v1_quotes
import spark.Spark.*

private val ROOT_REDIRECT = "https://github.com/dector/quotes"
private val DEFAULT_PORT = 1304

fun main(args: Array<String>) {
    port(getRunningPort())

    staticFileLocation("static/")

    get("/api/v1/quotes", v1_quotes)
}

fun getRunningPort(): Int {
    val processBuilder = ProcessBuilder()
    val port = processBuilder.environment()["PORT"]

    return if (port != null)
        port.toInt()
    else
        DEFAULT_PORT
}