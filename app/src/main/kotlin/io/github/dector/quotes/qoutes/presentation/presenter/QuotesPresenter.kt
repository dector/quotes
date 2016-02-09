package io.github.dector.quotes.qoutes.presentation.presenter

import io.github.dector.quotes.qoutes.model.DataProducer
import io.github.dector.quotes.qoutes.model.Quote
import io.github.dector.quotes.qoutes.presentation.ColorPair
import io.github.dector.quotes.qoutes.presentation.view.IQuotesActionListener
import io.github.dector.quotes.qoutes.presentation.view.QuotesView

class QuotesPresenter(val dataProducer: DataProducer) : IQuotesPresenter, IQuotesActionListener {

    lateinit var view: QuotesView

    fun init() {
        view.setup()

        dataProducer.observable.subscribe { displayQuote(it.first, it.second) }
    }

    override fun displayQuote() {
        dataProducer.next()
    }

    fun displayQuote(quote: Quote, colors: ColorPair) {
        view.showAuthor(quote.author)
        view.showQuote(quote.quote)

        view.textColor(colors.text)
        view.backgroundColor(colors.background)
    }
}