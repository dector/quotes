package io.github.dector.quotes.android

import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import io.github.dector.quotes.R
import io.github.dector.quotes.android.api.IApi
import io.github.dector.quotes.android.api.RetrofitApi
import io.github.dector.quotes.android.presentation.view.QuotesView
import io.github.dector.quotes.android.storage.ApiQuotesStorage
import io.github.dector.quotes.android.usecases.AsyncQuotesUseCase
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.presentation.presenter.QuotesPresenter
import io.github.dector.quotes.presentation.providers.ColorPairProvider
import io.github.dector.quotes.presentation.providers.IColorPairProvider
import io.github.dector.quotes.storage.CachedQuotesStorage
import io.github.dector.quotes.storage.IStorage
import io.github.dector.quotes.usecases.IQuotesUseCase

@Module
class QuotesModule() {

    @Provides
    fun api(): IApi = RetrofitApi()

    @Provides
    fun quotesStorage(api: IApi): IStorage<Quote>
            = CachedQuotesStorage(ApiQuotesStorage(api))

    @Provides
    fun quotesUseCase(storage: IStorage<Quote>): IQuotesUseCase
            = AsyncQuotesUseCase(storage)

    @Provides
    fun palette(): IColorPairProvider
            = ColorPairProvider()

    @Provides
    fun quotesPresenter(quotesUseCase: IQuotesUseCase, palette: IColorPairProvider)
            = QuotesPresenter(quotesUseCase, palette)

    @Provides
    fun quotesView(inflater: LayoutInflater)
            = QuotesView(inflater.inflate(R.layout.view_quotes, null))
}