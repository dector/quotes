package io.github.dector.quotes.presentation.presenter

import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.equalTo
import io.github.dector.quotes.presentation.providers.IColorPairProvider
import io.github.dector.quotes.presentation.view.Color
import io.github.dector.quotes.presentation.view.ColorPair
import io.github.dector.quotes.presentation.view.IQuotesView
import io.github.dector.quotes.usecases.IGetRandomQuoteUseCase
import org.jetbrains.spek.api.Spek

class QuotesPresenterSpec : Spek() { init {

    given("a presenter") {
        val colors = ColorPair(Color.WHITE, Color.BLACK)

        val useCase = MockQuoteUseCase()
        val view = MockQuotesView()

        val presenter = QuotesPresenter(useCase, MockPallete(colors))
        presenter.view = view

        on("initializing") {
            val quote = Quote("Wise words", "Smart being")
            useCase.quote = quote

            presenter.init()

            it("should initialize view") {
                view.initialized equalTo true
            }
            it ("should display quote") {
                view.quote equalTo quote.quote
                view.author equalTo quote.author
            }
            it ("should display colors") {
                view.textColor equalTo colors.text
                view.backgroundColor equalTo colors.background
            }
        }

        on("displaying quote") {
            val quote = Quote("Wise words", "Smart being")
            useCase.quote = quote

            presenter.displayQuote()

            it ("view should display data state") {
                view.dataStateShown equalTo true
            }
            it ("view should display quote") {
                view.quote equalTo quote.quote &&
                view.author equalTo quote.author
            }
            it ("view should display colors") {
                view.textColor equalTo colors.text &&
                view.backgroundColor equalTo colors.background
            }
        }

        on("displaying no quote") {
            useCase.quote = null

            presenter.displayQuote()

            it ("view should display empty state") {
                view.quote equalTo null &&
                view.author equalTo null
            }
            it ("view should display colors") {
                view.textColor equalTo colors.text &&
                view.backgroundColor equalTo colors.background
            }
        }
    }
}}

class MockQuoteUseCase(var quote: Quote? = null) : IGetRandomQuoteUseCase {

    override fun execute(callback: (Quote?) -> Unit) {
        callback(quote)
    }
}

class MockPallete(val colors: ColorPair) : IColorPairProvider {

    override fun getRandomColorPair() = colors
}

class MockQuotesView : IQuotesView {

    var initialized = false; private set
    var dataStateShown = false; private set
    var noDataStateShown = false; private set
    var quote: String? = null; private set
    var author: String? = null; private set
    var textColor: Color? = null; private set
    var backgroundColor: Color? = null; private set

    override fun init() { initialized = true }

    override fun showDataState() { dataStateShown = true }

    override fun showNoDataState() { noDataStateShown = true }

    override fun showQuote(quote: String) { this.quote = quote }

    override fun showAuthor(author: String) { this.author = author }

    override fun textColor(color: Color) { textColor = color }

    override fun backgroundColor(color: Color) { backgroundColor = color }
}