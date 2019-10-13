package io.github.dector.quotes.storage

import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.domain.Uuid

interface QuotesStorage {

    fun insert(quote: Quote)
    fun upsert(quote: Quote)

    fun find(uuid: Uuid): Quote?

    fun delete(uuid: Uuid)

    fun count(): Int
}
