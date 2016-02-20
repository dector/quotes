package io.github.dector.quote.api_server

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import io.github.dector.quotes.domain.Quote
import org.wasabi.app.AppServer

fun main(args: Array<String>) {
    val server = AppServer()

    server.get("/quotes", {
        response.send(getQuotesJsonString())
    })

    server.configuration.port = 1304
    server.start()
}

fun getQuotesJsonString(): String {
    val quotes = listOf(Quote("Zhinka, jak elektronika - lamajet'sja", "SoloWayKo"))

    return quotes.toJsonArray().toJsonString(true)
}

fun List<Quote>.toJsonArray(): JsonArray<JsonObject> {
    val list = map { it.toJson() }
    return JsonArray(list)
}

fun Quote.toJson() = JsonObject(mapOf("quote" to quote, "author" to author))