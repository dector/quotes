package io.github.dector.quotes.android.usecases

import android.os.Handler
import io.github.dector.quotes.common.random
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.repositories.IQuotesRepository
import io.github.dector.quotes.repositories.QuotesCriteria
import io.github.dector.quotes.usecases.IGetRandomQuoteUseCase
import io.github.dector.quotes.usecases.UseCaseResult

class GetRandomQuoteUseCase(val repository: IQuotesRepository,
                            val randomizer: List<Quote>.() -> Quote? = { this.random() }) : IGetRandomQuoteUseCase {

    override fun execute(callback: (UseCaseResult<Quote, Throwable>) -> Unit) {

        val handler = Handler()
        Thread {
            try {
                val data = repository
                        .get(QuotesCriteria.Anything())
                        .randomizer()

                handler.post { callback(UseCaseResult(data)) }
            } catch (e: Exception) {
                handler.post { callback(UseCaseResult(null, e)) }
            }
        }.start()
    }
}