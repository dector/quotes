package io.github.dector.quotes.qoutes.model

import io.github.dector.quotes.qoutes.storage.IQuotesStorage
import java.util.*

class QuotesFactory(private val storage: IQuotesStorage) {

    private val random = Random()
    private var lastQuote: Quote? = null

    fun randomQuote(): Quote {
        var newQuote = random()
        while (newQuote == lastQuote || newQuote == null)
            newQuote = random()

        lastQuote = newQuote
        return newQuote
    }

    private fun random() = storage[random.nextInt(storage.getCount())]
}