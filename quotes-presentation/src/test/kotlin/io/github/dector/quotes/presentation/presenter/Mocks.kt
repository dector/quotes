package io.github.dector.quotes.presentation.presenter

import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.presentation.providers.IColorPairProvider
import io.github.dector.quotes.presentation.view.Color
import io.github.dector.quotes.presentation.view.ColorPair
import io.github.dector.quotes.presentation.view.IQuotesView
import io.github.dector.quotes.usecases.IGetRandomQuoteUseCase

class MockQuoteUseCase(var quote: Quote? = null, val e: Throwable? = null) : IGetRandomQuoteUseCase {

    override fun execute(succeedCallback: (Quote?) -> Unit, failedCallback: (Throwable) -> Unit) {
        if (e == null) {
            succeedCallback(quote)
        } else {
            failedCallback(Throwable())
        }
    }
}

class MockPallete(val colors: ColorPair) : IColorPairProvider {

    override fun getRandomColorPair() = colors
}

class MockQuotesView : IQuotesView {

    var initialized = false; private set
    var dataStateShown = false; private set
    var errorMessage: String? = null; private set
    var noDataStateShown = false; private set
    var quote: String? = null; private set
    var author: String? = null; private set
    var textColor: Color? = null; private set
    var backgroundColor: Color? = null; private set

    override fun init() { initialized = true }

    override fun showDataState() { dataStateShown = true }

    override fun showNoDataState() { noDataStateShown = true }

    override fun showDisplayingError(message: String) { this.errorMessage = message }

    override fun showQuote(quote: String) { this.quote = quote }

    override fun showAuthor(author: String) { this.author = author }

    override fun textColor(color: Color) { textColor = color }

    override fun backgroundColor(color: Color) { backgroundColor = color }

    override fun showLoadingState() {
        TODO()
    }

    override fun showLoadingProgress() {
        TODO()
    }

    override fun hideLoadingProgress() {
        TODO()
    }

    override fun disableUserInteraction() {
        TODO()
    }

    override fun enableUserInteraction() {
        TODO()
    }
}
