package io.github.dector.quotes.qoutes.presentation.presenter

import io.github.dector.quotes.qoutes.model.DataProducer
import io.github.dector.quotes.qoutes.presentation.view.IQuotesActionListener
import io.github.dector.quotes.qoutes.presentation.view.QuotesView

class QuotesPresenter(val dataProducer: DataProducer) : IQuotesPresenter, IQuotesActionListener {

    lateinit var view: QuotesView

    fun init() {
        view.init()

        dataProducer.listen({ view.showNoDataState() }) { quote, colors ->
            view.showDataState()
            view.showAuthor(quote.author)
            view.showQuote(quote.quote)

            view.textColor(colors.text)
            view.backgroundColor(colors.background)
        }
    }

    override fun displayQuote() {
        dataProducer.next()
    }
}