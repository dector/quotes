package io.github.dector.quotes.qoutes

import android.content.Context
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import io.github.dector.quotes.R
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.presentation.presenter.QuotesPresenter
import io.github.dector.quotes.qoutes.presentation.view.QuotesView
import io.github.dector.qoutes.storage.MockQuotesStorage
import io.github.dector.quotes.storage.IStorage
import io.github.dector.quotes.usecases.IQuotesUseCase
import io.github.dector.quotes.usecases.QuotesUseCase

@Module
class QuotesModule() {

    @Provides
    fun quotesStorage(): IStorage<Quote?> = MockQuotesStorage()

    @Provides
    fun quotesUseCase(storage: IStorage<Quote?>): IQuotesUseCase = QuotesUseCase(storage)

    @Provides
    fun quotesPresenter(quotesUseCase: IQuotesUseCase) = QuotesPresenter(quotesUseCase)

    @Provides
    fun quotesView(context: Context) = QuotesView(LayoutInflater.from(context).inflate(R.layout.view_quotes, null))

}