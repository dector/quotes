package io.github.dector.quotes.presentation.presenter

import io.github.dector.quotes.presentation.view.IQuotesActionListener
import io.github.dector.quotes.presentation.view.IQuotesView
import io.github.dector.quotes.usecases.IQuotesUseCase

class QuotesPresenter(val quotesUseCase: IQuotesUseCase) : IQuotesPresenter, IQuotesActionListener {

    lateinit var view: IQuotesView

    fun init(preInit: () -> Unit) {
        preInit()
        displayQuote()
    }

    override fun displayQuote() {
        quotesUseCase.getRandomQuote { quote ->
            if (quote != null) {
                view.showDataState()
                view.showAuthor(quote.author)
                view.showQuote(quote.quote)
            } else {
                view.showNoDataState()
            }
        }
    }
}