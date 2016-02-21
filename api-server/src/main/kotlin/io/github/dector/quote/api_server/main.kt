package io.github.dector.quote.api_server

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import io.github.dector.quotes.domain.Quote
import spark.Spark.get
import spark.Spark.port

private val ROOT_REDIRECT = "https://github.com/dector/quotes"
private val DEFAULT_PORT = 1304

fun main(args: Array<String>) {
    port(getRunningPort())

    get("/", { req, resp ->
        "<html><head><title>Quotes</title></head><body>" +
                "<a href=\"https://github.com/dector/quotes\">Open github</a>" +
                "</body></html>"
    })

    get("/quotes", { req, resp ->
        resp.type("application/json")
        getQuotesJsonString()
    })
}

fun getRunningPort(): Int {
    val processBuilder = ProcessBuilder()
    val port = processBuilder.environment()["PORT"]

    return if (port != null)
        port.toInt()
    else
        DEFAULT_PORT
}

fun getQuotesJsonString(): String {
    val quotes = listOf(
            Quote("We live in a society exquisitely dependent on science and technology, in which hardly anyone knows anything about science and technology.", "Carl Sagan"),
            Quote("Only two things are infinite, the universe and human stupidity, and I'm not sure about the former.", "Albert Einstein"),
            Quote("Everything is theoretically impossible, until it is done.", "Robert A. Heinlein"),
            Quote("Most people say that it is the intellect which makes a great scientist. They are wrong: it is character.", "Albert Einstein"),
            Quote("All science requires mathematics. The knowledge of mathematical things is almost innate in us. This is the easiest of sciences, a fact which is obvious in that no one's brain rejects it; for laymen and people who are utterly illiterate know how to count and reckon.", "Roger Bacon"),
            Quote("Equipped with his five senses, man explores the universe around him and calls the adventure Science.", "Edwin Powell Hubble"))

    return quotes.toJsonArray().toJsonString(true)
}

fun List<Quote>.toJsonArray(): JsonArray<JsonObject> {
    val list = map { it.toJson() }
    return JsonArray(list)
}

fun Quote.toJson() = JsonObject(mapOf("quote" to quote, "author" to author))