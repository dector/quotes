package io.github.dector.quotes.repositories

import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.storage.IStorableQuotesRepository

class CachedQuotesRepository(mainRepo: IQuotesRepository,
                             cacheRepo: IStorableQuotesRepository,
                             cacheStrategy: ICacheStrategy) :
        CachedRepository<Quote>(mainRepo, cacheRepo, cacheStrategy), IQuotesRepository {
}