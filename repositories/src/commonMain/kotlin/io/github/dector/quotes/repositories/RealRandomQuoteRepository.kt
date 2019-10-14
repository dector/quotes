package io.github.dector.quotes.repositories

import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.storage.QuotesStorage

class RealRandomQuoteRepository(
    private val storage: QuotesStorage,
    private val randomizer: SimpleRandomizer<Int, Int> = IndexRandomizer
) : RandomQuoteRepository {

    override fun next(): Quote? {
        val size = storage.count()
        return if (size != 0)
            storage.findByIndex(randomIndex(size))
        else null
    }

    private fun randomIndex(size: Int): Int {
        if (size == 1) return 0
        return randomizer.next(size)
    }
}
