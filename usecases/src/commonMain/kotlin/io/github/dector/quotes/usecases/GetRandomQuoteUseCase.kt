package io.github.dector.quotes.usecases

import io.github.dector.knight.common.random
import io.github.dector.knight.usecases.AsyncUseCase
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.repositories.IQuotesRepository

class GetRandomQuoteUseCase(val repository: IQuotesRepository,
                            val randomizer: IQuotesRandomizer = SimpleQuotesRandomizer(),
                            jobExecutor: (() -> Unit) -> Unit = AsyncUseCase.plainExecutor,
                            callbackExecutor: (() -> Unit) -> Unit = AsyncUseCase.plainExecutor) :
        AsyncUseCase<Quote, Throwable>(jobExecutor, callbackExecutor), IGetRandomQuoteUseCase {

    override fun fetchData() = randomizer.get(repository.getAll())

    override fun fetchError(e: Throwable) = e
}

class SimpleQuotesRandomizer : IQuotesRandomizer {

    override fun get(data: List<Quote>) = data.random()
}

interface IQuotesRandomizer : IRandomizer<Quote>

interface IRandomizer<Data> {

    fun get(data: List<Data>): Data?
}