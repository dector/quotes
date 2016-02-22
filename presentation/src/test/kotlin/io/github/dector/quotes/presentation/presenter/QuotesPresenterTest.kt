package io.github.dector.quotes.presentation.presenter

import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.presentation.view.Color
import io.github.dector.quotes.presentation.view.ColorPair
import org.testng.Assert.*
import org.testng.annotations.Test

class QuotesPresenterTest {

    @Test fun init() {
        // Given
        val colors = ColorPair(Color.WHITE, Color.BLACK)
        val quote = Quote("Wise words", "Smart being")

        val useCase = MockQuoteUseCase(quote)
        val view = MockQuotesView()

        val presenter = QuotesPresenter(useCase, MockPallete(colors))
        presenter.view = view

        // When
        presenter.init()

        // Then
        assertTrue(view.initialized)
        assertTrue(view.dataStateShown)
        assertEquals(view.quote, quote.quote)
        assertEquals(view.author, quote.author)
        assertEquals(view.textColor, colors.text)
        assertEquals(view.backgroundColor, colors.background)
    }

    @Test fun displayQuote_withData() {
        // Given
        val colors = ColorPair(Color.WHITE, Color.BLACK)
        val quote = Quote("Wise words", "Smart being")

        val useCase = MockQuoteUseCase(quote)
        val view = MockQuotesView()

        val presenter = QuotesPresenter(useCase, MockPallete(colors))
        presenter.view = view

        // When
        presenter.displayQuote()

        // Then
        assertTrue(view.dataStateShown)
        assertEquals(view.quote, quote.quote)
        assertEquals(view.author, quote.author)
        assertEquals(view.textColor, colors.text)
        assertEquals(view.backgroundColor, colors.background)
    }

    @Test fun displayQuote_withoutData() {
        // Given
        val colors = ColorPair(Color.WHITE, Color.BLACK)
        val quote = null

        val useCase = MockQuoteUseCase(quote)
        val view = MockQuotesView()

        val presenter = QuotesPresenter(useCase, MockPallete(colors))
        presenter.view = view

        // When
        presenter.displayQuote()

        // Then
        assertTrue(view.noDataStateShown)
        assertNull(view.quote)
        assertNull(view.author)
        assertEquals(view.textColor, colors.text)
        assertEquals(view.backgroundColor, colors.background)
    }
}