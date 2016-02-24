package io.github.dector.quotes.usecases

import io.github.dector.knight.common.random
import io.github.dector.knight.usecases.AsyncUseCase
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.repositories.IQuotesRepository

class GetRandomQuoteUseCase(val repository: IQuotesRepository,
                            val randomizer: List<Quote>.() -> Quote? = { this.random() },
                            jobExecutor: (() -> Unit) -> Unit = AsyncUseCase.plainExecutor,
                            callbackExecutor: (() -> Unit) -> Unit = AsyncUseCase.plainExecutor) :
        AsyncUseCase<Quote, Throwable>(jobExecutor, callbackExecutor), IGetRandomQuoteUseCase {

    override fun fetchData() = repository
            .getAll()
            .randomizer()

    override fun fetchError(e: Throwable) = e
}