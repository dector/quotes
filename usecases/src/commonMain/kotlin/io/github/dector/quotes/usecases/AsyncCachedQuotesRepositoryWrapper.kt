package io.github.dector.quotes.usecases

import io.github.dector.knight.repositories.AsyncCachedRepositoryWrapper
import io.github.dector.knight.repositories.ICacheStrategy
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.repositories.IQuotesRepository
import io.github.dector.quotes.storage.IStorableQuotesRepository

class AsyncCachedQuotesRepositoryWrapper(mainRepo: IQuotesRepository,
                                         cacheRepo: IStorableQuotesRepository,
                                         cacheStrategy: ICacheStrategy,
                                         syncExecutor: (() -> Unit) -> Unit) :
        AsyncCachedRepositoryWrapper<Quote>(mainRepo, cacheRepo, cacheStrategy, syncExecutor), IQuotesRepository