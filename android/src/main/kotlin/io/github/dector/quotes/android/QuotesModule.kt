package io.github.dector.quotes.android

//import io.github.dector.quotes.usecases.GetRandomQuoteUseCase
import android.os.Handler
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import io.github.dector.quotes.R
import io.github.dector.quotes.android.presentation.view.QuotesView
import io.github.dector.quotes.android.repositories.RetrofitQuotesRepository
import io.github.dector.quotes.android.usecases.GetRandomQuoteUseCase
import io.github.dector.quotes.presentation.presenter.QuotesPresenter
import io.github.dector.quotes.presentation.providers.ColorPairProvider
import io.github.dector.quotes.presentation.providers.IColorPairProvider
import io.github.dector.quotes.repositories.IQuotesRepository
import io.github.dector.quotes.usecases.IGetRandomQuoteUseCase
import retrofit2.Retrofit

@Module
class QuotesModule() {

    @Provides fun quotesRepository(retrofit: Retrofit): IQuotesRepository
            = RetrofitQuotesRepository(retrofit)//MockQuotesRepository()

    /*@Provides fun threadProducer(): (() -> Unit) -> Thread
            = { Thread(it) }*/

    @Provides fun getRandomQuoteUseCase(repository: IQuotesRepository,
                                        mainHandler: Handler/*,
                                        threadProducer: (() -> Unit) -> Thread*/): IGetRandomQuoteUseCase
            = GetRandomQuoteUseCase(repository, jobExecutor = { Thread(it).start() },
                    callbackExecutor = { mainHandler.post(it) })

    @Provides fun palette(): IColorPairProvider
            = ColorPairProvider()

    @Provides fun quotesPresenter(getRandomQuoteUseCase: IGetRandomQuoteUseCase, palette: IColorPairProvider)
            = QuotesPresenter(getRandomQuoteUseCase, palette)

    @Provides fun quotesView(inflater: LayoutInflater)
            = QuotesView(inflater.inflate(R.layout.view_quotes, null))
}