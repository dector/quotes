package io.github.dector.quotes.presentation.presenter

import com.nhaarman.mockito_kotlin.reset
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.presentation.providers.IColorPairProvider
import io.github.dector.quotes.presentation.view.Color
import io.github.dector.quotes.presentation.view.ColorPair
import io.github.dector.quotes.presentation.view.IQuotesView
import io.github.dector.quotes.usecases.IQuotesUseCase
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.stubbing.OngoingStubbing
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test

class QuotesPresenterTest {

    @Mock
    lateinit var view: IQuotesView

    lateinit var useCase: IQuotesUseCase
    lateinit var palette: IColorPairProvider

    lateinit var presenter: QuotesPresenter

    // Data
    val quote = Quote("Wise words", "Smart being")
    val colors = ColorPair(Color.WHITE, Color.BLACK)

    @BeforeTest
    fun setUpTest() {
        initMocks(this)

        useCase = object : IQuotesUseCase {
            override fun getRandomQuote(callback: (Quote?) -> Unit) {
                callback(quote)
            }
        }

        palette = object : IColorPairProvider {
            override fun getRandomColorPair() = colors
        }

        presenter = QuotesPresenter(useCase, palette)
        presenter.view = view
    }

    @AfterTest
    fun tearDownTest() {
        reset(view)
    }

    @Test fun init() {
        presenter.init()

        view verifyAll {
            it().init()
        }
    }

    @Test fun displayQuote() {
        presenter.displayQuote()

        view verifyAll {
            it().showDataState()
            it().showAuthor(quote.author)
            it().showQuote(quote.quote)
            it().textColor(colors.text)
            it().backgroundColor(colors.background)
        }
    }

    inline infix fun <T> T.verifyAll(func: (() -> T) -> Unit) = func { Mockito.verify(this) }

    inline fun <T> OngoingStubbing<T>.thenNothing() { this.then {} }
}
