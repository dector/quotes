package io.github.dector.quotes.repositories

import io.github.dector.quotes.domain.Quote

interface RandomQuoteRepository {

    fun next(): Quote?
}
