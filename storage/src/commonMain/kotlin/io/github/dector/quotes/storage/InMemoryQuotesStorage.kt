package io.github.dector.quotes.storage

import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.domain.Uuid

class InMemoryQuotesStorage : QuotesStorage {

    private val data = mutableListOf<Quote>()

    override fun insert(quote: Quote) {
        val index = indexOf(quote.uuid)
        if (index != -1) error("Item with uuid '${quote.uuid}' already exists.")

        data += quote
    }

    override fun upsert(quote: Quote) {
        val index = indexOf(quote.uuid)

        if (index != -1)
            data[index] = quote
        else data += quote
    }

    override fun find(uuid: Uuid): Quote? =
        data.find { it.uuid == uuid }

    override fun delete(uuid: Uuid) {
        val index = indexOf(uuid)

        if (index != -1)
            data.removeAt(index)
    }

    override fun count(): Int =
        data.size

    private fun indexOf(uuid: Uuid) =
        data.indexOfFirst { it.uuid == uuid }
}
