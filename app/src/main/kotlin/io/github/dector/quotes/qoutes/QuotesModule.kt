package io.github.dector.quotes.qoutes

import dagger.Module
import dagger.Provides
import io.github.dector.quotes.qoutes.model.DataProducer
import io.github.dector.quotes.QuotesApplication
import io.github.dector.quotes.qoutes.presentation.presenter.QuotesPresenter

@Module
class QuotesModule(val app: QuotesApplication) {

    @Provides
    fun dataProducer() = DataProducer()

    @Provides
    fun quotesPresenter(dataProducer: DataProducer) = QuotesPresenter(dataProducer)

}