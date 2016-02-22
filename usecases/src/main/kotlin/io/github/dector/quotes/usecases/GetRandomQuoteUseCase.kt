package io.github.dector.quotes.usecases

import io.github.dector.quotes.common.random
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.repositories.IQuotesRepository
import io.github.dector.quotes.repositories.QuotesCriteria

class GetRandomQuoteUseCase(val repository: IQuotesRepository,
                            val randomizer: List<Quote>.() -> Quote? = { this.random() },
                            jobExecutor: (() -> Unit) -> Unit,
                            callbackExecutor: (() -> Unit) -> Unit) : AsyncUseCase<Quote, Throwable>(jobExecutor, callbackExecutor), IGetRandomQuoteUseCase {

    override fun fetchData(): Quote? {
        return repository
                .get(QuotesCriteria.Anything())
                .randomizer()
    }

    override fun onSucceed(data: Quote?, callback: (UseCaseResult<Quote, Throwable>) -> Unit) {
        callback(UseCaseResult(data))
    }

    override fun onFailed(e: Exception, callback: (UseCaseResult<Quote, Throwable>) -> Unit) {
        callback(UseCaseResult(null, e))
    }
}