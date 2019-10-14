package io.github.dector.quotes.android.repositories

import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.repositories.RandomQuoteRepository
import io.github.dector.quotes.storage.QuotesStorage
import kotlin.random.Random

class RealRandomQuoteRepository(
    private val storage: QuotesStorage
) : RandomQuoteRepository {

    private var prevIndex = -1

    override fun next(): Quote? {
        val size = storage.count()
        return if (size != 0)
            storage.findByIndex(randomIndex(size))
        else null
    }

    private fun randomIndex(until: Int): Int {
        if (until == 1) return 0

        var index = prevIndex
        while (index == prevIndex) {
            index = Random.nextInt(until)
        }

        prevIndex = index

        return index
    }
}
