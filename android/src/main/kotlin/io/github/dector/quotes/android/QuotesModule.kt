package io.github.dector.quotes.android

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import io.github.dector.knight.repositories.TimeCacheStrategy
import io.github.dector.quotes.R
import io.github.dector.quotes.android.network.AndroidNetworkManager
import io.github.dector.quotes.android.network.INetworkManager
import io.github.dector.quotes.android.presentation.view.QuotesView
import io.github.dector.quotes.android.repositories.FileStorableQuotesRepository
import io.github.dector.quotes.android.repositories.RealRandomQuoteRepository
import io.github.dector.quotes.android.repositories.RetrofitQuotesRepository
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.presentation.presenter.QuotesPresenter
import io.github.dector.quotes.presentation.providers.ColorPairProvider
import io.github.dector.quotes.presentation.providers.IColorPairProvider
import io.github.dector.quotes.repositories.IQuotesRepository
import io.github.dector.quotes.repositories.RandomQuoteRepository
import io.github.dector.quotes.storage.InMemoryQuotesStorage
import io.github.dector.quotes.storage.QuotesStorage
import io.github.dector.quotes.usecases.AsyncCachedQuotesRepositoryWrapper
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

    @Provides
    fun storage(): QuotesStorage =
        InMemoryQuotesStorage().apply {
            preStoredQuotesData().forEach(this::insert)
        }

    @Provides
    fun randomQuoteRepository(storage: QuotesStorage): RandomQuoteRepository =
        RealRandomQuoteRepository(storage)

    @Provides fun palette(): IColorPairProvider
            = ColorPairProvider()

    @Provides fun quotesPresenterConfiguration(context: Context) =
            QuotesPresenter.Configuration(errorMessage = context.getString(R.string.general_error))

    @Provides
    fun quotesPresenter(
        repo: RandomQuoteRepository,
        palette: IColorPairProvider,
        configuration: QuotesPresenter.Configuration
    ) = QuotesPresenter(repo, palette, configuration)

    @Provides fun quotesView(inflater: LayoutInflater)
            = QuotesView(inflater.inflate(R.layout.view_quotes, null))
}

private fun preStoredQuotesData() = listOf(
    Quote("We live in a society exquisitely dependent on science and technology, in which hardly anyone knows anything about science and technology.", "Carl Sagan"),
    Quote("Only two things are infinite, the universe and human stupidity, and I'm not sure about the former.", "Albert Einstein"),
    Quote("Everything is theoretically impossible, until it is done.", "Robert A. Heinlein"),
    Quote("Most people say that it is the intellect which makes a great scientist. They are wrong: it is character.", "Albert Einstein"),
    Quote("All science requires mathematics. The knowledge of mathematical things is almost innate in us. This is the easiest of sciences, a fact which is obvious in that no one's brain rejects it; for laymen and people who are utterly illiterate know how to count and reckon.", "Roger Bacon"),
    Quote("Equipped with his five senses, man explores the universe around him and calls the adventure Science.", "Edwin Powell Hubble")
)
