package io.github.dector.quotes.qoutes.model

import io.github.dector.quotes.qoutes.storage.IQuotesStorage
import io.github.dector.quotes.qoutes.storage.IStorage
import java.util.*

class QuotesFactory(private val storage: IQuotesStorage): IStorage<Quote> {

    private val random = Random()
    private var lastQuote: Quote? = null

    private fun randomQuote(): Quote? {
        val count = storage.getCount()

        return if (count > 0)
            storage[random.nextInt(count)]
        else
            null
    }

    override fun random(): Quote? {
        var newQuote: Quote?

        do { newQuote = randomQuote() } while (newQuote == lastQuote && newQuote != null && storage.getCount() > 1)

        lastQuote = newQuote
        return newQuote
    }
}