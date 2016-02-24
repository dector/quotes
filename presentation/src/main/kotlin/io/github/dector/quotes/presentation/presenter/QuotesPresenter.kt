package io.github.dector.quotes.presentation.presenter

import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.presentation.providers.IColorPairProvider
import io.github.dector.quotes.presentation.view.IQuotesActionListener
import io.github.dector.quotes.presentation.view.IQuotesView
import io.github.dector.quotes.usecases.IGetRandomQuoteUseCase

class QuotesPresenter(val getRandomQuoteUseCase: IGetRandomQuoteUseCase,
                      val palette: IColorPairProvider,
                      val configuration: Configuration? = null) :
        IQuotesPresenter, IQuotesActionListener {

    data class Configuration(val errorMessage: String = "")

    lateinit var view: IQuotesView

    private var state = DisplayingState.NO_DATA

    fun init() {
        view.init()
        nextQuote()
    }

    override fun nextQuote() {
        dataLoadingStarted()

        getRandomQuoteUseCase.execute({ quote ->
            dataLoadingFinished()
            onDataLoaded(quote)
        }, { error ->
            dataLoadingFinished()
            view.showDisplayingError(error.message ?: configuration?.errorMessage ?: "")
        })
    }

    private fun dataLoadingStarted() {
        when (state) {
            DisplayingState.DATA -> Unit
            DisplayingState.NO_DATA -> view.showLoadingState()
        }
    }

    private fun dataLoadingFinished() {
        when (state) {
            DisplayingState.DATA -> view.showDataState()
            DisplayingState.NO_DATA -> view.showNoDataState()
        }
    }

    private fun onDataLoaded(quote: Quote?) {
        displayQuote(quote)
        changeColors()
    }

    private fun displayQuote(quote: Quote?) {
        if (quote != null) {
            view.showAuthor(quote.author)
            view.showQuote(quote.quote)

            state = DisplayingState.DATA
        } else {
            state = DisplayingState.NO_DATA
        }
    }

    private fun changeColors() {
        palette.getRandomColorPair().let { colors ->
            view.textColor(colors.text)
            view.backgroundColor(colors.background)
        }
    }

    private enum class DisplayingState {
        DATA, NO_DATA
    }
}