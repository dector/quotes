package io.github.dector.quotes.qoutes.model

import io.github.dector.quotes.qoutes.presentation.ColorPair
import io.github.dector.quotes.qoutes.presentation.Palette
import rx.Subscriber
import rx.lang.kotlin.observable

class DataProducer {

    private var subscriber: Subscriber<in Pair<Quote, ColorPair>>? = null

    private val quotes = QuotesFactory()
    private val palette = Palette()

    private val observable = observable<Pair<Quote, ColorPair>> { subscriber ->
        this.subscriber = subscriber
        next()
    }

    fun listen (f: (Quote, ColorPair) -> Unit) {
        observable.subscribe { f(it.first, it.second) }
    }

    fun next() {
        val subscriber = this.subscriber ?: return

        if (!subscriber.isUnsubscribed)
            subscriber.onNext(Pair(quotes.randomQuote(), palette.random()))
    }
}