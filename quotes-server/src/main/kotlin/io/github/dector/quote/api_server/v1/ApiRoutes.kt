package io.github.dector.quote.api_server.v1

import io.github.dector.quote.api_server.getQuotesJsonString
import io.ktor.application.ApplicationCall
import io.ktor.http.ContentType
import io.ktor.response.respondText

suspend fun ApplicationCall.sendAllQuotes() {
    respondText(
        text = getQuotesJsonString(),
        contentType = ContentType.Application.Json,
    )
}
