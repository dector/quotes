package io.github.dector.quotes.android

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Handler
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import io.github.dector.knight.common.random
import io.github.dector.knight.repositories.TimeCacheStrategy
import io.github.dector.quotes.R
import io.github.dector.quotes.android.network.AndroidNetworkManager
import io.github.dector.quotes.android.network.INetworkManager
import io.github.dector.quotes.android.presentation.view.QuotesView
import io.github.dector.quotes.android.repositories.FileStorableQuotesRepository
import io.github.dector.quotes.android.repositories.RetrofitQuotesRepository
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.presentation.presenter.QuotesPresenter
import io.github.dector.quotes.presentation.providers.ColorPairProvider
import io.github.dector.quotes.presentation.providers.IColorPairProvider
import io.github.dector.quotes.repositories.IQuotesRepository
import io.github.dector.quotes.usecases.AsyncCachedQuotesRepositoryWrapper
import io.github.dector.quotes.usecases.GetRandomQuoteUseCase
import io.github.dector.quotes.usecases.IGetRandomQuoteUseCase
import io.github.dector.quotes.usecases.IQuotesRandomizer
import retrofit2.Retrofit

@Module
class QuotesModule() {

    private val QUOTES_SHARED_PREFERENCES = "quotes_data"

    @Provides fun quotesSharedPreferences(context: Context): SharedPreferences
            = context.getSharedPreferences(QUOTES_SHARED_PREFERENCES, Context.MODE_PRIVATE)

    @Provides fun networkManager(cm: ConnectivityManager): INetworkManager
            = AndroidNetworkManager(cm)

    @Provides fun quotesRepository(retrofit: Retrofit, networkManager: INetworkManager, quotesSharedPreferences: SharedPreferences): IQuotesRepository
//            = CachedQuotesRepository(RetrofitQuotesRepository(retrofit), ListStorableQuotesRepository(), TimeCacheStrategy())
            = AsyncCachedQuotesRepositoryWrapper(RetrofitQuotesRepository(retrofit, networkManager),
            FileStorableQuotesRepository(quotesSharedPreferences),
            TimeCacheStrategy(),
            { Thread(it).start()})
//            = RetrofitQuotesRepository(retrofit)

    @Provides fun quotesRandomizer(): IQuotesRandomizer = object : IQuotesRandomizer {
        private var prevQuote: Quote? = null

        override fun get(data: List<Quote>): Quote? {
            var quote = randomQuote(data)

            if (data.size != 1) {
                while (quote == prevQuote && prevQuote != null) {
                    quote = randomQuote(data)
                }
            }

            prevQuote = quote
            return quote
        }

        private fun randomQuote(data: List<Quote>) = data.random()
    }

    @Provides fun getRandomQuoteUseCase(repository: IQuotesRepository,
                                        mainHandler: Handler, randomizer: IQuotesRandomizer): IGetRandomQuoteUseCase
            = GetRandomQuoteUseCase(repository, jobExecutor = { Thread(it).start() },
            callbackExecutor = { mainHandler.post(it) }, randomizer = randomizer)

    @Provides fun palette(): IColorPairProvider
            = ColorPairProvider()

    @Provides fun quotesPresenterConfiguration(context: Context) =
            QuotesPresenter.Configuration(errorMessage = context.getString(R.string.general_error))

    @Provides fun quotesPresenter(getRandomQuoteUseCase: IGetRandomQuoteUseCase, palette: IColorPairProvider,
                                  configuration: QuotesPresenter.Configuration)
            = QuotesPresenter(getRandomQuoteUseCase, palette, configuration)

    @Provides fun quotesView(inflater: LayoutInflater)
            = QuotesView(inflater.inflate(R.layout.view_quotes, null))
}