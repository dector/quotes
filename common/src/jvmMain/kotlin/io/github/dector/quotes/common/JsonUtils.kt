package io.github.dector.quotes.common

import com.eclipsesource.json.Json
import com.eclipsesource.json.JsonArray
import com.eclipsesource.json.JsonObject
import com.eclipsesource.json.WriterConfig
import io.github.dector.quotes.domain.Quote

actual fun String.toQuotesList(): List<Quote> =
    Json.parse(this)
        .asArray()
        .map { it.asObject().toQuote() }

actual fun List<Quote>.toJsonString(): String =
    this.map(Quote::toJsonObject)
        .fold(JsonArray()) { array, obj -> array.add(obj) }
        .toString(WriterConfig.MINIMAL)

private fun JsonObject.toQuote() =
    Quote(
        quote = this.getString("quote", null),
        author = this.getString("author", null)
    )

private fun Quote.toJsonObject() =
    JsonObject()
        .add("quote", this.quote)
        .add("author", this.author)
