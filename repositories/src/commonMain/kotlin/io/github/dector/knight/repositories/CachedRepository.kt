package io.github.dector.knight.repositories

import io.github.dector.knight.common.currentTimeMillis
import io.github.dector.knight.common.minutesAsMillis
import io.github.dector.knight.storage.IStorableRepository

interface ICacheStrategy {

    fun isCacheValid(): Boolean

    fun setCacheUpdated() {}
}

class AlwaysActualDataCacheStrategy : ICacheStrategy {
    override fun isCacheValid() = false
}

class AlwaysCachedDataCacheStrategy : ICacheStrategy {
    override fun isCacheValid() = true
}

class TimeCacheStrategy(val validityTimeMs: Long = 5.minutesAsMillis()) : ICacheStrategy {

    private var nextUpdateTime = 0L

    override fun isCacheValid() = currentTimeMillis() < nextUpdateTime

    override fun setCacheUpdated() { nextUpdateTime = currentTimeMillis() + validityTimeMs }
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
        if (! cacheStrategy.isCacheValid() && mainRepo.isAvailable()) {
            cacheRepo.removeAll()

            val data = mainRepo.getAll()
            cacheRepo.saveAll(data)

            cacheStrategy.setCacheUpdated()
        }
    }
}
