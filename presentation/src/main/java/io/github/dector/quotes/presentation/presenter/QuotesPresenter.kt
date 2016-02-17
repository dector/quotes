package io.github.dector.quotes.presentation.presenter

import io.github.dector.quotes.presentation.providers.IColorPairProvider
import io.github.dector.quotes.presentation.view.IQuotesActionListener
import io.github.dector.quotes.presentation.view.IQuotesView
import io.github.dector.quotes.usecases.IQuotesUseCase

class QuotesPresenter(val quotesUseCase: IQuotesUseCase,
                      val palette: IColorPairProvider) : IQuotesPresenter, IQuotesActionListener {

    lateinit var view: IQuotesView

    fun init() {
        view.init()
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

        palette.getRandomColorPair().let {
            view.textColor(it.text)
            view.backgroundColor(it.background)
        }
    }
}