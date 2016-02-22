package io.github.dector.quotes.presentation.presenter

import io.github.dector.quotes.presentation.providers.IColorPairProvider
import io.github.dector.quotes.presentation.view.IQuotesActionListener
import io.github.dector.quotes.presentation.view.IQuotesView
import io.github.dector.quotes.usecases.IGetRandomQuoteUseCase

class QuotesPresenter(val getRandomQuoteUseCase: IGetRandomQuoteUseCase,
                      val palette: IColorPairProvider) : IQuotesPresenter, IQuotesActionListener {

    lateinit var view: IQuotesView

    fun init() {
        view.init()
        displayQuote()
    }

    override fun displayQuote() {
        getRandomQuoteUseCase.execute { result ->
            if (result.isSuccessful()) {
                val quote = result.data

                if (quote != null) {
                    view.showDataState()
                    view.showAuthor(quote.author)
                    view.showQuote(quote.quote)
                } else {
                    view.showNoDataState()
                }

                palette.getRandomColorPair().let {
                    view.textColor(it.text)
                    view.backgroundColor(it.background)
                }
            } else {
                view.showDisplayingError(result.error?.message ?: "Oops. I can't do it :(")
            }
        }
    }
}