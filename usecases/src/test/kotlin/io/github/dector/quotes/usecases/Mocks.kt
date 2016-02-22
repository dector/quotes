package io.github.dector.quotes.usecases

import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.repositories.IQuotesRepository
import io.github.dector.quotes.repositories.QuotesCriteria

class MockQuotesRepository(val data: List<Quote> = emptyList()) : IQuotesRepository {

    override fun count(criteria: QuotesCriteria) = data.size.toLong()

    override fun get(criteria: QuotesCriteria) = data
}