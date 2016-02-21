package io.github.dector.quotes.repositories

import io.github.dector.quotes.domain.Quote

interface IQuotesRepository : IRepository<Quote, QuotesCriteria>

sealed class QuotesCriteria {
    class Anything : QuotesCriteria()
}