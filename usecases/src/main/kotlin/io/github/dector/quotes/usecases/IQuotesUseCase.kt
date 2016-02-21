package io.github.dector.quotes.usecases

import io.github.dector.quotes.domain.Quote

interface IQuotesUseCase {

    fun getRandomQuote(callback: (Quote?) -> Unit)
}