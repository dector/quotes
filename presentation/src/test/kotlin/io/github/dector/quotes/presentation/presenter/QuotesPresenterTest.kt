package io.github.dector.quotes.presentation.presenter

import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.presentation.providers.IColorPairProvider
import io.github.dector.quotes.presentation.view.Color
import io.github.dector.quotes.presentation.view.ColorPair
import io.github.dector.quotes.presentation.view.IQuotesView
import io.github.dector.quotes.usecases.IQuotesUseCase
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
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

    @Captor
    val usecaseCaptor: ArgumentCaptor<(Quote?) -> Unit> = captor()

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

    @Test
    fun init() {
        val colors = ColorPair(Color.BLACK, Color.WHITE)
        val quote = Quote("wise text", "author name")

        whenever(view.init()).thenNothing()
        whenever(useCase.getRandomQuote(usecaseCaptor.capture())).thenNothing()
        usecaseCaptor.value.invoke(quote)
        whenever(palette.getRandomColorPair()).thenReturn(colors)

        presenter.init()

        verify(view).showDataState()
        verify(view).showAuthor(quote.author)
        verify(view).showQuote(quote.quote)
        verify(view).textColor(colors.text)
        verify(view).backgroundColor(colors.background)

        noMoreInteractions()
    }

    fun noMoreInteractions() {
        verifyNoMoreInteractions(view)
        verifyNoMoreInteractions(useCase)
        verifyNoMoreInteractions(palette)
    }

    inline fun <T> OngoingStubbing<T>.thenNothing() { this.then {} }

    inline fun <reified T : Any> captor() = ArgumentCaptor.forClass<T, T>(T::class.java)
}
