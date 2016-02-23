package io.github.dector.quotes.repositories

import io.github.dector.quotes.common.minutesAsMillis
import io.github.dector.quotes.storage.IStorableRepository

interface ICacheStrategy {

    fun isCacheValid(): Boolean

    fun setCacheUpdated() {}
}

class AlwaysActualDataCacheStrategy : ICacheStrategy {

    override fun isCacheValid() = false
}

class TimeCacheStrategy(val validityTimeMs: Int = 5.minutesAsMillis()) : ICacheStrategy {

    private var nextUpdateTime = 0L

    override fun isCacheValid() = System.currentTimeMillis() < nextUpdateTime

    override fun setCacheUpdated() { nextUpdateTime = System.currentTimeMillis() + validityTimeMs }
}

abstract class CachedRepository<Data>(val mainRepo: IRepository<Data>,
                                                val cacheRepo: IStorableRepository<Data>,
                                                val cacheStrategy: ICacheStrategy = AlwaysActualDataCacheStrategy()) : IRepository<Data> {
    override fun size(): Long {
        validateCache()

        return cacheRepo.size()
    }

    override fun getAll(): List<Data> {
        validateCache()

        return cacheRepo.getAll()
    }

    private fun validateCache() {
        if (! cacheStrategy.isCacheValid()) {
            cacheRepo.removeAll()

            val data = mainRepo.getAll()
            cacheRepo.saveAll(data)

            cacheStrategy.setCacheUpdated()
        }
    }
}