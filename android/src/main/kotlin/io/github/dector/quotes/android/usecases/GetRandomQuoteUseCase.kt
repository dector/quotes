package io.github.dector.quotes.android.usecases

import io.github.dector.quotes.common.random
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.repositories.IQuotesRepository
import io.github.dector.quotes.repositories.QuotesCriteria
import io.github.dector.quotes.usecases.IGetRandomQuoteUseCase
import io.github.dector.quotes.usecases.UseCaseResult

class GetRandomQuoteUseCase(val repository: IQuotesRepository,
                            val randomizer: List<Quote>.() -> Quote? = { this.random() },
                            val jobExecutor: (() -> Unit) -> Unit = { it() },
                            val callbackExecutor: (() -> Unit) -> Unit = { it() }) : IGetRandomQuoteUseCase {

    override fun execute(callback: (UseCaseResult<Quote, Throwable>) -> Unit) {
        jobExecutor {
            try {
                val data = repository
                        .get(QuotesCriteria.Anything())
                        .randomizer()

                callbackExecutor { callback(UseCaseResult(data)) }
            } catch (e: Exception) {
                callbackExecutor { callback(UseCaseResult(null, e)) }
            }
        }
    }
}