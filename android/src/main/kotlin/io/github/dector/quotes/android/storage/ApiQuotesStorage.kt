package io.github.dector.quotes.android.storage

import io.github.dector.quotes.android.api.IApi
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.storage.IStorage

class ApiQuotesStorage(val api: IApi) : IStorage<Quote> {

    override fun all() = api.quotes()

    override fun count() = all().size

    override fun get(index: Int) = all()[index]
}