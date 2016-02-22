package io.github.dector.quotes.repositories

import io.github.dector.quotes.storage.IStorableRepository
import io.github.dector.quotes.storage.ListCriteria

interface ICacheStrategy {

    fun isCacheValid(): Boolean
}

class AlwaysActualDataCacheStrategy : ICacheStrategy {

    override fun isCacheValid() = false
}

abstract class CachedRepository<Data>(val mainRepo: IRepository<Data>,
                                                val cacheRepo: IStorableRepository<Data>,
                                                val cacheStrategy: ICacheStrategy = AlwaysActualDataCacheStrategy()) : IRepository<Data> {

    override fun count(criteria: ListCriteria): Long {
        validateCache(criteria)

        return cacheRepo.count(criteria)
    }

    override fun get(criteria: ListCriteria): List<Data> {
        validateCache(criteria)

        return cacheRepo.get(criteria)
    }

    private fun validateCache(criteria: ListCriteria) {
        if (! cacheStrategy.isCacheValid()) {
            cacheRepo.remove(criteria)

            val data = mainRepo.get(criteria)
            cacheRepo.save(criteria, data)
        }
    }
}