package io.github.dector.knight.repositories

import io.github.dector.knight.storage.IStorableRepository

abstract class AsyncCachedRepositoryWrapper<Data>(
    val mainRepo: IRepository<Data>,
    val cacheRepo: IStorableRepository<Data>,
    val cacheStrategy: ICacheStrategy = AlwaysActualDataCacheStrategy(),
    val syncExecutor: (() -> Unit) -> Unit = { it() }
) : IRepository<Data> {

    private var syncInProgress = false

    override fun size(): Long {
        val result = cacheRepo.size()

        validateCache()

        return result
    }

    override fun getAll(): List<Data> {
        val result = cacheRepo.getAll()

        validateCache()

        return result
    }

    private fun validateCache() {
        if (cacheStrategy.isCacheValid() || syncInProgress || !mainRepo.isAvailable())
            return

        syncExecutor {
            if (!syncInProgress) {
                syncInProgress = true
                return@syncExecutor
            }

            try {
                val data = mainRepo.getAll()
                cacheRepo.removeAll()
                cacheRepo.saveAll(data)

                cacheStrategy.setCacheUpdated()
            } finally {
                syncInProgress = false
            }
        }
    }
}
