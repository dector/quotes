package io.github.dector.quotes.presentation.presenter

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
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

    @Mock
    lateinit var useCase: IQuotesUseCase

    @Mock
    lateinit var palette: IColorPairProvider

    lateinit var presenter: QuotesPresenter

    @BeforeTest
    fun setUpTest() {
        initMocks(this)
        presenter = QuotesPresenter(useCase, palette)
        presenter.view = view
    }

    @AfterTest
    fun tearDownTest() {
        reset(useCase)
        reset(palette)
        reset(view)
    }

    @Test fun init() {
        whenever(palette.getRandomColorPair()).thenReturn(ColorPair(Color.WHITE, Color.BLACK))

        presenter.init()

        view verifyAll {
            it().init()
        }

        /*noMoreInteractions()*/
    }

    @Test fun displayQuote() {
        val colors = ColorPair(Color.WHITE, Color.BLACK)
        whenever(palette.getRandomColorPair()).thenReturn(colors)

        presenter.displayQuote()

        view verifyAll {
            /*it().showDataState()
            it().showAuthor(quote.author)
            it().showQuote(quote.quote)
            it().textColor(colors.text)
            it().backgroundColor(colors.background)*/

            it().textColor(colors.text)
            it().backgroundColor(colors.background)
        }
        useCase verifyAll {
            it().getRandomQuote(any())
        }

        palette verifyAll {
            it().getRandomColorPair()
        }

        noMoreInteractions()
    }

    fun noMoreInteractions() {
        verifyNoMoreInteractions(view)
        verifyNoMoreInteractions(useCase)
        verifyNoMoreInteractions(palette)
    }

    inline infix fun <T> T.verifyAll(func: (() -> T) -> Unit) = func { Mockito.verify(this) }

    inline fun <T> OngoingStubbing<T>.thenNothing() { this.then {} }
}
