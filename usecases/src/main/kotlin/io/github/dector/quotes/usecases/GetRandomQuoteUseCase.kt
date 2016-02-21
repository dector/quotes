package io.github.dector.quotes.usecases

import io.github.dector.quotes.common.random
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.repositories.IQuotesRepository
import io.github.dector.quotes.repositories.QuotesCriteria

class GetRandomQuoteUseCase(val repository: IQuotesRepository) : IGetRandomQuoteUseCase {

    override fun execute(callback: (Quote?) -> Unit) {
        callback(repository.get(QuotesCriteria.Anything()).random())
    }
}