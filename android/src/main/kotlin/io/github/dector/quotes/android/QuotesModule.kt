package io.github.dector.quotes.android

import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import io.github.dector.quotes.R
import io.github.dector.quotes.android.presentation.view.QuotesView
import io.github.dector.quotes.android.repositories.RetrofitQuotesRepository
import io.github.dector.quotes.presentation.presenter.QuotesPresenter
import io.github.dector.quotes.presentation.providers.ColorPairProvider
import io.github.dector.quotes.presentation.providers.IColorPairProvider
import io.github.dector.quotes.repositories.CachedQuotesRepository
import io.github.dector.quotes.repositories.IQuotesRepository
import io.github.dector.knight.repositories.TimeCacheStrategy
import io.github.dector.quotes.android.repositories.FileStorableQuotesRepository
import io.github.dector.quotes.storage.ListStorableQuotesRepository
import io.github.dector.quotes.usecases.GetRandomQuoteUseCase
import io.github.dector.quotes.usecases.IGetRandomQuoteUseCase
import retrofit2.Retrofit

@Module
class QuotesModule() {

    private val QUOTES_SHARED_PREFERENCES = "quotes_data"

    @Provides fun quotesSharedPreferences(context: Context): SharedPreferences
            = context.getSharedPreferences(QUOTES_SHARED_PREFERENCES, Context.MODE_PRIVATE)

    @Provides fun quotesRepository(retrofit: Retrofit, quotesSharedPreferences: SharedPreferences): IQuotesRepository
//            = CachedQuotesRepository(RetrofitQuotesRepository(retrofit), ListStorableQuotesRepository(), TimeCacheStrategy())
            = CachedQuotesRepository(RetrofitQuotesRepository(retrofit), FileStorableQuotesRepository(quotesSharedPreferences), TimeCacheStrategy())
//            = RetrofitQuotesRepository(retrofit)

    @Provides fun getRandomQuoteUseCase(repository: IQuotesRepository,
                                        mainHandler: Handler): IGetRandomQuoteUseCase
            = GetRandomQuoteUseCase(repository, jobExecutor = { Thread(it).start() },
            callbackExecutor = { mainHandler.post(it) })

    @Provides fun palette(): IColorPairProvider
            = ColorPairProvider()

    @Provides fun quotesPresenter(getRandomQuoteUseCase: IGetRandomQuoteUseCase, palette: IColorPairProvider)
            = QuotesPresenter(getRandomQuoteUseCase, palette)

    @Provides fun quotesView(inflater: LayoutInflater)
            = QuotesView(inflater.inflate(R.layout.view_quotes, null))
}