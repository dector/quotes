package io.github.dector.quotes.common

import io.github.dector.quotes.domain.Quote

expect fun String.toQuotesList(): List<Quote>

expect fun List<Quote>.toJsonString(): String
