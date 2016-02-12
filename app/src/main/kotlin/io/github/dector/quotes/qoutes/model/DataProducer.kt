package io.github.dector.quotes.qoutes.model

import io.github.dector.quotes.qoutes.presentation.ColorPair
import io.github.dector.quotes.qoutes.presentation.Palette
import io.github.dector.quotes.qoutes.storage.IQuotesStorage
import rx.Subscriber
import rx.lang.kotlin.observable

class DataProducer(storage: IQuotesStorage) {

    private var subscriber: Subscriber<in Pair<Quote, ColorPair>>? = null

    private val quotes = QuotesFactory(storage)
    private val palette = Palette()

    private val observable = observable<Pair<Quote, ColorPair>> { subscriber ->
        this.subscriber = subscriber
        next()
    }

    fun listen (error: () -> Unit, consumer: (Quote, ColorPair) -> Unit) {
        observable.subscribe(
                { consumer(it.first, it.second) },
                { error() })
    }

    fun next() {
        val subscriber = this.subscriber ?: return

        if (!subscriber.isUnsubscribed) {
            val quote = quotes.randomQuote()
            val color = palette.random()

            if (quote != null) {
                subscriber.onNext(Pair(quote, color))
            } else {
                subscriber.onError(RuntimeException("No data"))
            }
        }
    }
}