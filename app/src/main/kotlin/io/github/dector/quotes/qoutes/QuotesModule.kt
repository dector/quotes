package io.github.dector.quotes.qoutes

import android.content.Context
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import io.github.dector.quotes.R
import io.github.dector.quotes.qoutes.model.Quote
import io.github.dector.quotes.qoutes.model.QuotesFactory
import io.github.dector.quotes.qoutes.presentation.ColorPair
import io.github.dector.quotes.qoutes.presentation.Palette
import io.github.dector.quotes.qoutes.presentation.presenter.QuotesPresenter
import io.github.dector.quotes.qoutes.presentation.view.QuotesView
import io.github.dector.quotes.qoutes.storage.IStorage
import io.github.dector.quotes.qoutes.storage.MockQuotesStorage

@Module
class QuotesModule() {

    @Provides
    fun quotesStorage(): IStorage<Quote> = QuotesFactory(MockQuotesStorage())

    @Provides
    fun colorsStorage(): IStorage<ColorPair> = Palette()

    @Provides
    fun quotesPresenter(quotesStorage: IStorage<Quote>, colorsStorage: IStorage<ColorPair>) = QuotesPresenter(quotesStorage, colorsStorage)

    @Provides
    fun quotesView(context: Context) = QuotesView(LayoutInflater.from(context).inflate(R.layout.view_quotes, null))

}