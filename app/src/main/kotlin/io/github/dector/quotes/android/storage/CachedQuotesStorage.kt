package io.github.dector.quotes.android.storage

import io.github.dector.quotes.android.common.add
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.storage.IStorage
import java.util.*
import java.util.concurrent.TimeUnit

class CachedQuotesStorage(val delegate: IStorage<Quote>) : IStorage<Quote> {

    private val cache = HashMap<Int, Quote>()

    private var validUntil = Date()

    override fun count(): Int {
        validateCache()
        return cache.size
    }

    override fun all(): List<Quote> {
        validateCache()
        return cache.values.toList()
    }

    override fun get(index: Int): Quote? {
        validateCache()

        return cache[index]
    }

    private fun validateCache() {
        if (System.currentTimeMillis() <= validUntil.time) {
            return
        }

        val newData = try {
            var index = 0
            delegate.all().map { index++ to it }
        } catch (e: Exception) {
            null
        }

        if (newData != null) {
            cache.clear()
            cache.putAll(newData)
            validUntil = createNewValidTime()
        }
    }

    private fun createNewValidTime() = Date().add(15, TimeUnit.MINUTES)
}