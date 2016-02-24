package io.github.dector.knight.repositories

import io.github.dector.knight.storage.IStorableRepository
import java.util.*

class MockRepository<Data>(val data: List<Data> = emptyList(),
                           val available: Boolean = true) : IRepository<Data> {

    override fun size() = data.size.toLong()

    override fun getAll() = data

    override fun isAvailable() = available
}

class MockStorableRepository<Data>(initialData: List<Data> = emptyList(),
                                   val available: Boolean = true) : IStorableRepository<Data> {

    private val data: MutableList<Data> = ArrayList(initialData)

    override fun size() = data.size.toLong()

    override fun getAll() = data.toList()

    override fun save(data: Data) = this.data.add(data)

    override fun saveAll(data: List<Data>) = this.data.addAll(data)

    override fun remove(data: Data) = this.data.remove(data)

    override fun removeAll() = { data.clear(); true }()

    override fun isAvailable() = available
}

data class TestData(val id: String)

class CachedRepositoryImpl<Data>(mainRepo: IRepository<Data>,
                           cacheRepo: IStorableRepository<Data>,
                           cacheStrategy: ICacheStrategy = AlwaysActualDataCacheStrategy()) :
        CachedRepository<Data>(mainRepo, cacheRepo, cacheStrategy) {
}