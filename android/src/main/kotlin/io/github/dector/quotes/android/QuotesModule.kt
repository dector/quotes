package io.github.dector.quotes.android

import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import io.github.dector.quotes.R
import io.github.dector.quotes.android.api.IApi
import io.github.dector.quotes.android.api.RetrofitApi
import io.github.dector.quotes.android.presentation.view.QuotesView
import io.github.dector.quotes.presentation.presenter.QuotesPresenter
import io.github.dector.quotes.presentation.providers.ColorPairProvider
import io.github.dector.quotes.presentation.providers.IColorPairProvider
import io.github.dector.quotes.repositories.IQuotesRepository
import io.github.dector.quotes.repositories.MockQuotesRepository
import io.github.dector.quotes.usecases.GetRandomQuoteUseCase
import io.github.dector.quotes.usecases.IGetRandomQuoteUseCase

@Module
class QuotesModule() {

    @Provides fun api(): IApi
            = RetrofitApi()

    @Provides fun quotesRepository(): IQuotesRepository
            = MockQuotesRepository()

    @Provides fun getRandomQuoteUseCase(repository: IQuotesRepository): IGetRandomQuoteUseCase
            = GetRandomQuoteUseCase(repository)

    @Provides fun palette(): IColorPairProvider
            = ColorPairProvider()

    @Provides fun quotesPresenter(getRandomQuoteUseCase: IGetRandomQuoteUseCase, palette: IColorPairProvider)
            = QuotesPresenter(getRandomQuoteUseCase, palette)

    @Provides fun quotesView(inflater: LayoutInflater)
            = QuotesView(inflater.inflate(R.layout.view_quotes, null))
}