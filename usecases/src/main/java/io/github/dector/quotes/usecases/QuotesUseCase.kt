package io.github.dector.quotes.usecases

import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.storage.IStorage
import java.util.*

class QuotesUseCase(val storage: IStorage<Quote?>) : IQuotesUseCase {

    val random = Random()

    override fun getRandomQuote(callback: (Quote?) -> Unit) {
        val index = random.nextInt(storage.count())
        callback(storage[index])
    }
}

