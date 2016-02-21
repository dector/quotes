package io.github.dector.quotes.android.storage

import io.github.dector.quotes.android.api.IApi
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.storage.IStorage

class ApiQuotesStorage(val api: IApi) : IStorage<Quote?> {

    private fun load() = api.quotes()

    override fun count() = load().size

    override fun get(index: Int) = load()[index]
}