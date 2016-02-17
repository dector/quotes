package io.github.dector.quotes.android

import android.content.Context
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import io.github.dector.quotes.R
import io.github.dector.quotes.android.presentation.view.QuotesView
import io.github.dector.quotes.android.providers.ColorPairProvider
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.presentation.presenter.QuotesPresenter
import io.github.dector.quotes.presentation.providers.IColorPairProvider
import io.github.dector.quotes.storage.IStorage
import io.github.dector.quotes.storage.MockQuotesStorage
import io.github.dector.quotes.usecases.IQuotesUseCase
import io.github.dector.quotes.usecases.QuotesUseCase

@Module
class QuotesModule() {

    @Provides
    fun quotesStorage(): IStorage<Quote?> = MockQuotesStorage()

    @Provides
    fun quotesUseCase(storage: IStorage<Quote?>): IQuotesUseCase = QuotesUseCase(storage)

    @Provides
    fun palette(): IColorPairProvider = ColorPairProvider()

    @Provides
    fun layoutInflater(context: Context) = LayoutInflater.from(context)

    @Provides
    fun quotesPresenter(quotesUseCase: IQuotesUseCase, palette: IColorPairProvider)
            = QuotesPresenter(quotesUseCase, palette)

    @Provides
    fun quotesView(inflater: LayoutInflater) = QuotesView(inflater.inflate(R.layout.view_quotes, null))

}